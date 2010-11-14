package info.bliki.wiki.tags.code;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLCodeFilter extends AbstractCPPBasedCodeFilter implements SourceCodeFormatter {
  private final static String BREAK = "<br/>";

  private static final String[] KEYWORDS = {
          "alter",
          "and",
          "blob",
          "boolean",
          "character",
          "clob",
          "column",
          "constraint",
          "create",
          "default",
          "delete",
          "drop",
          "false",
          "from",
          "in",
          "insert",
          "integer",
          "key",
          "lob",
          "not",
          "null",
          "or",
          "procedure",
          "references",
          "select",
          "set",
          "table",
          "timestamp",
          "true",
          "update",
          "varchar",
          "where",
  };

  private static HashMap<String, String> KEYWORD_SET =
          new HashMap<String, String>();

  static {
          for (String k : KEYWORDS) {
                  createHashMap(KEYWORD_SET, k);
          }
  }

  public SQLCodeFilter() {
          // empty
  }

  /**
  * @return Returns the KEYWORD_SET.
  */
  @Override
  public HashMap<String, String> getKeywordSet() {
          return KEYWORD_SET;
  }

  /**
  * @return Returns the OBJECT_SET.
  */
  @Override
  public HashMap<String, String> getObjectSet() {
          return null;
  }

  Pattern wp = Pattern.compile("[a-z]+", Pattern.MULTILINE);
  // Pattern cp = Pattern.compile("^\\s*--.*$", Pattern.MULTILINE);

  /** Do the work of filtering one chunk of code in a <source> type element
   */
  @Override
  public String filter(String input) {
          // Sanitize the entire input first, then only changes
          // made are from final literal strings in this code
          StringBuilder antiCSSsb = new StringBuilder();
          for (char ch : input.toCharArray()) {
                  appendChar(antiCSSsb, ch);
          }
          Matcher wm = wp.matcher(antiCSSsb);
          StringBuffer sb = new StringBuffer();

          HashMap<String, String> keywordSet = getKeywordSet();

          while (wm.find()) {
                  String word = wm.group(0);
                  if (keywordSet.get(word.toLowerCase()) != null) {
                          wm.appendReplacement(sb, FONT_KEYWORD + word + FONT_END);
                  } else {
                          wm.appendReplacement(sb, word);
                  }
          }
          wm.appendTail(sb);

          String ret = sb.toString();

          return ret.replaceAll("\n", BREAK);

  }

  @Override
  public boolean isKeywordCaseSensitive() {
          return false;
  }
}


