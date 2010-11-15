package info.bliki.wiki.filter;

import info.bliki.htmlcleaner.Utils;
import info.bliki.wiki.model.Configuration;
import info.bliki.wiki.model.IWikiModel;
import info.bliki.wiki.tags.util.WikiTagNode;
import info.bliki.wiki.template.ITemplateFunction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * A template parser for the first pass in the parsing of a Wikipedia text
 * 
 * @see WikipediaParser for the second pass
 */
public class TemplateParser extends AbstractParser {
	private static final Pattern HTML_COMMENT_PATTERN = Pattern.compile("<!--(.*?)-->");

	public final boolean fParseOnlySignature;

	private final boolean fRenderTemplate;

	private boolean fOnlyIncludeFlag;

	public TemplateParser(String stringSource) {
		this(stringSource, false, false);
	}

	public TemplateParser(String stringSource, boolean parseOnlySignature, boolean renderTemplate) {
		super(stringSource);
		fParseOnlySignature = parseOnlySignature;
		fRenderTemplate = renderTemplate;
		fOnlyIncludeFlag = false;
	}

	public static void parse(String rawWikitext, IWikiModel wikiModel, Appendable writer, boolean renderTemplate) throws IOException {
		parse(rawWikitext, wikiModel, writer, false, renderTemplate);
	}

	/**
	 * Parse the wiki texts templates, comments and signatures into the given
	 * <code>StringBuilder</code>.
	 * 
	 * @param rawWikitext
	 * @param wikiModel
	 * @param writer
	 * @param parseOnlySignature
	 *          change only the signature string and ignore templates and comments
	 *          parsing
	 * @param renderTemplate
	 */
	public static void parse(String rawWikitext, IWikiModel wikiModel, Appendable writer, boolean parseOnlySignature,
			boolean renderTemplate) throws IOException {
		parseRecursive(rawWikitext, wikiModel, writer, parseOnlySignature, renderTemplate);
	}

	// private static Pattern noinclude =
	// Pattern.compile("<noinclude[^>]*>.*?<\\\\/noinclude[^>]*>");
	//
	// private static Pattern INCLUDEONLY_PATTERN =
	// Pattern.compile("<includeonly[^>]*>(.*?)<\\/includeonly[^>]*>");

	protected static void parseRecursive(String rawWikitext, IWikiModel wikiModel, Appendable writer, boolean parseOnlySignature,
			boolean renderTemplate) throws IOException {
		parseRecursive(rawWikitext, wikiModel, writer, parseOnlySignature, renderTemplate, null);
	}

	public static void parseRecursive(String rawWikitext, IWikiModel wikiModel, Appendable writer, boolean parseOnlySignature,
			boolean renderTemplate, Map<String, String> templateParameterMap) throws IOException {
		try {
			int level = wikiModel.incrementRecursionLevel();
			if (level > Configuration.PARSER_RECURSION_LIMIT) {
				writer.append("Error - recursion limit exceeded parsing templates.");
				return;
			}

			// recursion limit on level is not sufficient as it is possible to recurse
			// indefinitely at fixed level upper bound
			if (wikiModel.incrementTemplateRecursionCount() > Configuration.TEMPLATE_RECURSION_LIMIT) {
				// writer.append("Error - total recursion count limit (" +
				// wikiModel.getTemplateRecursionCount() +
				// ") exceeded parsing templates.");
				return;
			}

			TemplateParser parser = new TemplateParser(rawWikitext, parseOnlySignature, renderTemplate);
			parser.setModel(wikiModel);
			StringBuilder sb = new StringBuilder(rawWikitext.length());
			parser.runPreprocessParser(sb, false);
			if (parseOnlySignature) {
				writer.append(sb);
				return;
			}

			int len = sb.length();
			parser = new TemplateParser(sb.toString(), false, renderTemplate);
			parser.setModel(wikiModel);
			sb = new StringBuilder(len);
			parser.runPreprocessParser(sb, true);

			StringBuilder parameterBuffer = sb;
			StringBuilder plainBuffer = sb;
			if (templateParameterMap != null && (!templateParameterMap.isEmpty())) {
				String preprocessedContent = parameterBuffer.toString();
				WikipediaScanner scanner = new WikipediaScanner(preprocessedContent);
				scanner.setModel(wikiModel);
				parameterBuffer = scanner.replaceTemplateParameters(preprocessedContent, templateParameterMap);
				if (parameterBuffer != null) {
					plainBuffer = parameterBuffer;
				}
			}
			parser = new TemplateParser(plainBuffer.toString(), parseOnlySignature, renderTemplate);
			parser.setModel(wikiModel);
			// parser.initialize(plainBuffer.toString());
			sb = new StringBuilder(plainBuffer.length());
			parser.runParser(sb);

			if (!renderTemplate) {
				String redirectedLink = AbstractParser.parseRedirect(sb.toString(), wikiModel);
				if (redirectedLink != null) {
					String redirectedContent = AbstractParser.getRedirectedTemplateContent(wikiModel, redirectedLink, null);
					if (redirectedContent != null) {
						parseRecursive(redirectedContent, wikiModel, writer, parseOnlySignature, renderTemplate);
						return;
					}
				}
			}
			writer.append(sb);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			writer.append(e.getClass().getSimpleName());
		} catch (Error e) {
			e.printStackTrace();
			writer.append(e.getClass().getSimpleName());
		} finally {
			wikiModel.decrementRecursionLevel();
		}
	}

	/**
	 * Preprocess parsing of the <code>&lt;includeonly&gt;</code>,
	 * <code>&lt;onlyinclude&gt;</code> and <code>&lt;noinclude&gt;</code> tags
	 * 
	 * @param writer
	 * @param ignoreTemplateTags
	 *          TODO
	 * @throws IOException
	 */
	protected void runPreprocessParser(StringBuilder writer, boolean ignoreTemplateTags) throws IOException {
		fWhiteStart = true;
		fWhiteStartPosition = fCurrentPosition;
		try {
			while (fCurrentPosition < fSource.length) {
				char ch = fSource[fCurrentPosition++];

				// ---------Identify the next token-------------
				switch (ch) {
				case '<':
					int htmlStartPosition = fCurrentPosition;
					if (!fParseOnlySignature && parseIncludeWikiTags(writer, ignoreTemplateTags)) {
						continue;
					}
					fCurrentPosition = htmlStartPosition;
					break;
				case '~':
					int tildeCounter = 0;
					if (fSource[fCurrentPosition] == '~' && fSource[fCurrentPosition + 1] == '~') {
						// parse signatures '~~~', '~~~~' or '~~~~~'
						tildeCounter = 3;
						try {
							if (fSource[fCurrentPosition + 2] == '~') {
								tildeCounter = 4;
								if (fSource[fCurrentPosition + 3] == '~') {
									tildeCounter = 5;
								}
							}
						} catch (IndexOutOfBoundsException e1) {
							// end of scanner text
						}
						appendContent(writer, fWhiteStart, fWhiteStartPosition, 1, true);
						fWikiModel.appendSignature(writer, tildeCounter);
						fCurrentPosition += (tildeCounter - 1);
						fWhiteStart = true;
						fWhiteStartPosition = fCurrentPosition;
					}
				}

				if (!fWhiteStart) {
					fWhiteStart = true;
					fWhiteStartPosition = fCurrentPosition - 1;
				}

			}
			if(fCurrentPosition == fSource.length)
				fCurrentPosition++;
			// -----------------end switch while try--------------------
		} catch (IndexOutOfBoundsException e) {
			// end of scanner text
		}
		try {
			if (!fOnlyIncludeFlag) {
				appendContent(writer, fWhiteStart, fWhiteStartPosition, 1, true);
			}
		} catch (IndexOutOfBoundsException e) {
			// end of scanner text
		}
	}

	protected void runParser(Appendable writer) throws IOException {
		// int oldCurrentPosition = 0;
		fWhiteStart = true;
		fWhiteStartPosition = fCurrentPosition;
		try {
			while (fCurrentPosition < fSource.length) {
				// if (oldCurrentPosition >= fCurrentPosition) {
				// System.out.println("stop stop: " + oldCurrentPosition + "--" +
				// fStringSource);
				// System.exit(-1);
				// }
				char ch = fSource[fCurrentPosition++];

				// oldCurrentPosition = fCurrentPosition;
				// ---------Identify the next token-------------
				switch (ch) {
				case '{': // wikipedia template handling
					if (!fParseOnlySignature && parseTemplateOrTemplateParameter(writer)) {
						fWhiteStart = true;
						fWhiteStartPosition = fCurrentPosition;
						continue;
					}
					break;

				case '<':
					int htmlStartPosition = fCurrentPosition;
					if (!fParseOnlySignature && parseSpecialWikiTags(writer)) {
						continue;
					}
					fCurrentPosition = htmlStartPosition;
					break;
				case '~':
					int tildeCounter = 0;
					if (fSource[fCurrentPosition] == '~' && fSource[fCurrentPosition + 1] == '~') {
						// parse signatures '~~~', '~~~~' or '~~~~~'
						tildeCounter = 3;
						try {
							if (fSource[fCurrentPosition + 2] == '~') {
								tildeCounter = 4;
								if (fSource[fCurrentPosition + 3] == '~') {
									tildeCounter = 5;
								}
							}
						} catch (IndexOutOfBoundsException e1) {
							// end of scanner text
						}
						appendContent(writer, fWhiteStart, fWhiteStartPosition, 1, true);
						fWikiModel.appendSignature(writer, tildeCounter);
						fCurrentPosition += (tildeCounter - 1);
						fWhiteStart = true;
						fWhiteStartPosition = fCurrentPosition;
					}
				}

				if (!fWhiteStart) {
					fWhiteStart = true;
					fWhiteStartPosition = fCurrentPosition - 1;
				}

			}
			if(fCurrentPosition == fSource.length)
				fCurrentPosition++;
			// -----------------end switch while try--------------------
		} catch (IndexOutOfBoundsException e) {
			// end of scanner text
		}
		try {
			appendContent(writer, fWhiteStart, fWhiteStartPosition, 1, true);
		} catch (IndexOutOfBoundsException e) {
			// end of scanner text
		}
	}

	/**
	 * See <a href=
	 * "http://en.wikipedia.org/wiki/Help:Template#Controlling_what_gets_transcluded"
	 * >Help:Template#Controlling what gets transcluded</a>
	 * 
	 * @param writer
	 * @param ignoreTemplateTags
	 *          TODO
	 * @return
	 * @throws IOException
	 */
	protected boolean parseIncludeWikiTags(StringBuilder writer, boolean ignoreTemplateTags) throws IOException {
		try {
			switch (fSource[fCurrentPosition]) {
			case '!': // <!-- html comment -->
				if (parseHTMLCommentTags(writer)) {
					return true;
				}
				break;
			default:

				if (fSource[fCurrentPosition] != '/') {
					// starting tag
					int lessThanStart = fCurrentPosition - 1;
					WikiTagNode tagNode = parseTag(fCurrentPosition);
					if (tagNode != null) {
						fCurrentPosition = tagNode.getEndPosition();
						int tagStart = fCurrentPosition;
						String tagName = tagNode.getTagName();
						if (tagName.equals("nowiki")) {
							if (readUntilIgnoreCase("</", "nowiki>")) {
								return true;
							}
						} else if (tagName.equals("source")) {
							if (readUntilIgnoreCase("</", "source>")) {
								return true;
							}
						} else if (tagName.equals("math")) {
							if (readUntilIgnoreCase("</", "math>")) {
								return true;
							}
						}
						if (ignoreTemplateTags) {
							return false;
						}
						if (!isTemplate()) {
							// not rendering a Template namespace directly
							if (tagName.equals("includeonly")) {
								if (readUntilIgnoreCase("</", "includeonly>")) {
									if (!fOnlyIncludeFlag) {
										appendContent(writer, fWhiteStart, fWhiteStartPosition, fCurrentPosition - lessThanStart, true);
									}
									fWhiteStart = true;
									fWhiteStartPosition = tagStart;

									if (!fOnlyIncludeFlag) {
										appendContent(writer, fWhiteStart, fWhiteStartPosition, 2 + "includeonly>".length(), true);
									}
									fWhiteStart = true;
									fWhiteStartPosition = fCurrentPosition;
									return true;
								}

								if (!fOnlyIncludeFlag) {
									appendContent(writer, fWhiteStart, fWhiteStartPosition, fCurrentPosition - lessThanStart, true);
								}
								fWhiteStart = false;
								// fWhiteStartPosition = tagStart;
								fCurrentPosition = fStringSource.length();
								return true;

							} else if (tagName.equals("noinclude")) {
								if (readUntilIgnoreCase("</", "noinclude>")) {
									if (!fOnlyIncludeFlag) {
										appendContent(writer, fWhiteStart, fWhiteStartPosition, fCurrentPosition - lessThanStart, true);
									}
									fWhiteStart = true;
									fWhiteStartPosition = fCurrentPosition;
									return true;
								}
								appendContent(writer, fWhiteStart, fWhiteStartPosition, fCurrentPosition - lessThanStart, true);
								fWhiteStart = true;
								fWhiteStartPosition = tagStart;
								return true;
							} else if (tagName.equals("onlyinclude")) {
								if (readUntilIgnoreCase("</", "onlyinclude>")) {
									if (!fOnlyIncludeFlag) {
										// delete the content, which is already added
										writer.delete(0, writer.length());
										fOnlyIncludeFlag = true;
									}

									// appendContent(writer, fWhiteStart, fWhiteStartPosition,
									// fCurrentPosition - lessThanStart);
									fWhiteStart = true;
									fWhiteStartPosition = tagStart;

									appendContent(writer, fWhiteStart, fWhiteStartPosition, 2 + "onlyinclude>".length(), true);
									fWhiteStart = true;
									fWhiteStartPosition = fCurrentPosition;
									return true;
								}
								appendContent(writer, fWhiteStart, fWhiteStartPosition, fCurrentPosition - lessThanStart, true);
								fWhiteStart = true;
								fWhiteStartPosition = tagStart;
								return true;
							}
						} else {
							if (tagName.equals("noinclude")) {
								if (readUntilIgnoreCase("</", "noinclude>")) {
									appendContent(writer, fWhiteStart, fWhiteStartPosition, fCurrentPosition - lessThanStart, true);
									fWhiteStart = true;
									fWhiteStartPosition = tagStart;

									appendContent(writer, fWhiteStart, fWhiteStartPosition, 2 + "noinclude>".length(), true);
									fWhiteStart = true;
									fWhiteStartPosition = fCurrentPosition;
									return true;
								}
								appendContent(writer, fWhiteStart, fWhiteStartPosition, fCurrentPosition - lessThanStart, true);
								fWhiteStart = true;
								fWhiteStartPosition = tagStart;
								return true;
							} else if (tagName.equals("includeonly")) {
								if (readUntilIgnoreCase("</", "includeonly>")) {
									appendContent(writer, fWhiteStart, fWhiteStartPosition, fCurrentPosition - lessThanStart, true);
									fWhiteStart = true;
									fWhiteStartPosition = fCurrentPosition;
									return true;
								}

								appendContent(writer, fWhiteStart, fWhiteStartPosition, fCurrentPosition - lessThanStart, true);
								fWhiteStart = false;
								// fWhiteStartPosition = tagStart;
								fCurrentPosition = fStringSource.length();
								return true;
							} else if (tagName.equals("onlyinclude")) {
								if (readUntilIgnoreCase("</", "onlyinclude>")) {
									appendContent(writer, fWhiteStart, fWhiteStartPosition, fCurrentPosition - lessThanStart, true);
									fWhiteStart = true;
									fWhiteStartPosition = fCurrentPosition;
									return true;
								}
								appendContent(writer, fWhiteStart, fWhiteStartPosition, fCurrentPosition - lessThanStart, true);
								fWhiteStart = true;
								fWhiteStartPosition = tagStart;
								return true;
							}
						}
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			// do nothing
		}
		return false;
	}

	protected boolean parseSpecialWikiTags(Appendable writer) throws IOException {
		try {
			switch (fSource[fCurrentPosition]) {
			case '!': // <!-- html comment -->
				if (parseHTMLCommentTags(writer)) {
					return true;
				}
				break;
			default:

				if (fSource[fCurrentPosition] != '/') {
					// starting tag
					WikiTagNode tagNode = parseTag(fCurrentPosition);
					if (tagNode != null) {
						fCurrentPosition = tagNode.getEndPosition();
						String tagName = tagNode.getTagName();
						if (tagName.equals("nowiki")) {
							if (readUntilIgnoreCase("</", "nowiki>")) {
								return true;
							}
						} else if (tagName.equals("source")) {
							if (readUntilIgnoreCase("</", "source>")) {
								return true;
							}
						} else if (tagName.equals("math")) {
							if (readUntilIgnoreCase("</", "math>")) {
								return true;
							}
						}
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			// do nothing
		}
		return false;
	}

	protected void appendContent(Appendable writer, boolean whiteStart, final int whiteStartPosition, final int diff,
			boolean stripHTMLComments) throws IOException {
		if (whiteStart) {
			try {
				final int whiteEndPosition = fCurrentPosition - diff;
				int count = whiteEndPosition - whiteStartPosition;
				if (count > 0) {
					if (stripHTMLComments) {
						writer.append(HTML_COMMENT_PATTERN.matcher(fStringSource.substring(whiteStartPosition, whiteEndPosition))
								.replaceAll(""));
					} else {
						writer.append(fStringSource, whiteStartPosition, whiteEndPosition);
					}
				}
			} finally {
				fWhiteStart = false;
			}
		}
	}

	private boolean parseTemplateOrTemplateParameter(Appendable writer) throws IOException {
		if (fSource[fCurrentPosition] == '{') {
			appendContent(writer, fWhiteStart, fWhiteStartPosition, 1, true);
			int startTemplatePosition = ++fCurrentPosition;
			if (fSource[fCurrentPosition] == '{' && fSource[fCurrentPosition + 1] != '{') {
				// parse template parameters
				int[] templateEndPosition = findNestedParamEnd(fSource, fCurrentPosition + 1);
				if (templateEndPosition[0] < 0) {
					if (templateEndPosition[1] < 0) {
						--fCurrentPosition;
					} else {
						writer.append('{');
						++fCurrentPosition;
						return parseTemplate(writer, startTemplatePosition + 1, templateEndPosition[1]);
					}
				} else {
					return parseTemplateParameter(writer, startTemplatePosition, templateEndPosition[0]);
				}
			} else {
				int templateEndPosition = findNestedTemplateEnd(fSource, fCurrentPosition);
				if (templateEndPosition < 0) {
					fCurrentPosition--;
				} else {
					return parseTemplate(writer, startTemplatePosition, templateEndPosition);
				}
			}
		}
		return false;
	}

	/**
	 * Parse a single template call {{...}}. There are 3 main steps:
	 * <ol>
	 * <li>Check if the call is a parser function in the
	 * <code>checkParserFunction()</code> method; if <code>true</code> execute the
	 * parser function and return</li>
	 * <li>Split the template call in the <code>createParameterMap()</code method
	 * into a <code>templateName</code> and a parameter/value map.</li>
	 * <li>Substitute the raw template text into th existing text and replace all
	 * template parameters with their value in
	 * <code>TemplateParser.parseRecursive()</code method.</li>
	 * </ol>
	 * 
	 * @param writer
	 * @param startTemplatePosition
	 * @param templateEndPosition
	 * @return
	 * @throws IOException
	 */
	private boolean parseTemplate(Appendable writer, int startTemplatePosition, int templateEndPosition) throws IOException {
		fCurrentPosition = templateEndPosition;
		// insert template handling
		int endPosition = fCurrentPosition;
		String plainContent = null;
		int endOffset = fCurrentPosition - 2;
		Object[] objs = createParameterMap(fSource, startTemplatePosition, fCurrentPosition - startTemplatePosition - 2);
		List<String> parts = (List<String>) objs[0];
		String templateName = ((String) objs[1]).trim();
		StringBuilder buf = new StringBuilder((templateName.length()) + (templateName.length() / 10));
		TemplateParser.parse(templateName, fWikiModel, buf, false);
		templateName = buf.toString();
		int currOffset = checkParserFunction(templateName);
		if (currOffset > 0) {
			String function = templateName.substring(0, currOffset - 1).trim();
			if (function != null) {
				ITemplateFunction templateFunction = fWikiModel.getTemplateFunction(function);
				if (templateFunction != null) {
					// if (function.charAt(0) == '#') {
					// #if:, #ifeq:,...
					parts.set(0, templateName.substring(currOffset));
					plainContent = templateFunction.parseFunction(parts, fWikiModel, fSource, startTemplatePosition + currOffset, endOffset);
					fCurrentPosition = endPosition;
					if (plainContent != null) {
						TemplateParser.parseRecursive(plainContent, fWikiModel, writer, false, false);
						return true;
					}
					return true;
				}
				fCurrentPosition = endOffset + 2;
			}
		}
		fCurrentPosition = endPosition;
		LinkedHashMap<String, String> parameterMap = new LinkedHashMap<String, String>();
		for (int i = 1; i < parts.size(); i++) {
			if (i == parts.size() - 1) {
				createSingleParameter(i, parts.get(i), parameterMap, true);
			} else {
				createSingleParameter(i, parts.get(i), parameterMap, false);
			}
		}
		fWikiModel.substituteTemplateCall(templateName, parameterMap, writer);
		return true;
	}

	/**
	 * Parse a single template parameter {{{...}}}
	 * 
	 * @param writer
	 * @param startTemplatePosition
	 * @param templateEndPosition
	 * @return
	 * @throws IOException
	 */
	private boolean parseTemplateParameter(Appendable writer, int startTemplatePosition, int templateEndPosition) throws IOException {
		String plainContent = fStringSource.substring(startTemplatePosition - 2, templateEndPosition);

		if (plainContent != null) {
			fCurrentPosition = templateEndPosition;
			WikipediaScanner scanner = new WikipediaScanner(plainContent);
			scanner.setModel(fWikiModel);
			StringBuilder plainBuffer = scanner.replaceTemplateParameters(plainContent, null);
			if (plainBuffer == null) {
				writer.append(plainContent);
				return true;
			}
			TemplateParser.parseRecursive(plainBuffer.toString().trim(), fWikiModel, writer, false, false);
			return true;
		}
		return false;
	}

	/**
	 * Create a map from the parameters defined in a template call
	 * 
	 * @return the templates parameters <code>java.util.List</code> at index [0];
	 *         the template name at index [1]
	 * 
	 */
	private static Object[] createParameterMap(char[] src, int startOffset, int len) {
		Object[] objs = new Object[2];
		int currOffset = startOffset;
		int endOffset = startOffset + len;
		List<String> resultList = new ArrayList<String>();
		objs[0] = resultList;
		resultList = splitByPipe(src, currOffset, endOffset, resultList);
		if (resultList.size() <= 1) {
			// set the template name
			objs[1] = new String(src, startOffset, len);
		} else {
			objs[1] = resultList.get(0);
		}
		return objs;
	}

	/**
	 * Create a single parameter defined in a template call and add it to the
	 * parameters map
	 * 
	 */
	private static void createSingleParameter(int parameterCounter, String srcString, Map<String, String> map,
			boolean trimNewlineRight) {
		int currOffset = 0;
		char[] src = srcString.toCharArray();
		int endOffset = srcString.length();
		char ch;
		String parameter = null;
		String value;
		boolean equalCharParsed = false;

		int lastOffset = currOffset;
		int[] temp = new int[] { -1, -1 };
		try {
			while (currOffset < endOffset) {
				ch = src[currOffset++];
				if (ch == '[' && src[currOffset] == '[') {
					currOffset++;
					temp[0] = findNestedEnd(src, '[', ']', currOffset);
					if (temp[0] >= 0) {
						currOffset = temp[0];
					}
				} else if (ch == '{' && src[currOffset] == '{') {
					currOffset++;
					if (src[currOffset] == '{' && src[currOffset + 1] != '{') {
						currOffset++;
						temp = findNestedParamEnd(src, currOffset);
						if (temp[0] >= 0) {
							currOffset = temp[0];
						} else {
							currOffset--;
							temp[0] = findNestedTemplateEnd(src, currOffset);
							if (temp[0] >= 0) {
								currOffset = temp[0];
							}
						}
					} else {
						temp[0] = findNestedTemplateEnd(src, currOffset);
						if (temp[0] >= 0) {
							currOffset = temp[0];
						}
					}
				} else if (ch == '=') {
					if (!equalCharParsed) {
						parameter = srcString.substring(lastOffset, currOffset - 1).trim();
						lastOffset = currOffset;
					}
					equalCharParsed = true;
				}
			}

		} catch (IndexOutOfBoundsException e) {

		} finally {
			if (currOffset > lastOffset) {
				if (trimNewlineRight) {
					value = Utils.trimNewlineRight(srcString.substring(lastOffset, currOffset));
				} else {
					value = srcString.substring(lastOffset, currOffset).trim();
				}
				if (parameter != null) {
					map.put(parameter, value);
				} else {
					String intParameter = Integer.toString(parameterCounter);
					map.put(intParameter, value);
				}
			}
		}
	}

	/**
	 * Check if this template contains a template function
	 * 
	 * Note: repositions this#fCurrentPosition behind the parser function string
	 * if possible
	 * 
	 * @param startOffset
	 * @param endOffset
	 * @return the parser function name (without the # character) or
	 *         <code>null</code> if no parser function can be found in this
	 *         template
	 */
	// private String checkParserFunction(int startOffset, int endOffset) {
	// // String function = null;
	// int currOffset = startOffset;
	// int functionStart = startOffset;
	// char ch;
	// while (currOffset < endOffset) {
	// ch = fSource[currOffset++];
	// if (Character.isLetter(ch) || ch == '#' || ch == '$') {
	// functionStart = currOffset - 1;
	// while (currOffset < endOffset) {
	// ch = fSource[currOffset++];
	// if (ch == ':') {
	// fCurrentPosition = currOffset;
	// return fStringSource.substring(functionStart, currOffset - 1);
	// } else if (!Character.isLetterOrDigit(ch) && ch != '$') {
	// return null;
	// }
	// }
	// break;
	// } else if (!Character.isWhitespace(ch)) {
	// return null;
	// }
	// }
	// return null;
	// }

	/**
	 * Check if this template contains a template function
	 * 
	 * Note: repositions this#fCurrentPosition behind the parser function string
	 * if possible
	 * 
	 * @param plainContent
	 * @return the offset behind the &acute;:&acute; character at the end of the
	 *         parser function name or <code>-1</code> if no parser function can
	 *         be found in this template.
	 */
	private int checkParserFunction(String plainContent) {
		// String function = null;
		int currOffset = 0;
		// int functionStart = 0;
		int len = plainContent.length();
		char ch;
		while (currOffset < len) {
			ch = plainContent.charAt(currOffset++);
			if (Character.isLetter(ch) || ch == '#' || ch == '$') {
				// functionStart = currOffset - 1;
				while (currOffset < len) {
					ch = plainContent.charAt(currOffset++);
					if (ch == ':') {
						// fCurrentPosition = currOffset;
						// return plainContent.substring(functionStart, currOffset - 1);
						return currOffset;
					} else if (!Character.isLetterOrDigit(ch) && ch != '$') {
						return -1;
					}
				}
				break;
			} else if (!Character.isWhitespace(ch)) {
				return -1;
			}
		}
		return -1;
	}

	protected boolean parseHTMLCommentTags(Appendable writer) throws IOException {
		int temp = readWhitespaceUntilStartOfLine(2);
		String htmlCommentString = fStringSource.substring(fCurrentPosition - 1, fCurrentPosition + 3);
		if (htmlCommentString.equals("<!--")) {
			if (temp >= 0) {
				if (!fOnlyIncludeFlag) {
					appendContent(writer, fWhiteStart, fWhiteStartPosition, fCurrentPosition - temp - 1, true);
				}
			} else {
				if (!fOnlyIncludeFlag) {
					appendContent(writer, fWhiteStart, fWhiteStartPosition, 1, true);
				}
			}
			fCurrentPosition += 3;
			if (readUntil("-->")) {
				if (temp >= 0) {
					temp = readWhitespaceUntilEndOfLine(0);
					if (temp >= 0) {
						fCurrentPosition++;
					}
				}
				fWhiteStart = true;
				fWhiteStartPosition = fCurrentPosition;
				return true;
			}
		}
		return false;
	}

	@Override
	public void runParser() {
		// do nothing here
	}

	@Override
	public void setNoToC(boolean noToC) {
		// do nothing here
	}

	public boolean isTemplate() {
		return fRenderTemplate;
	}

}
