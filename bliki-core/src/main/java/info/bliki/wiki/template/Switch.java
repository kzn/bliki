package info.bliki.wiki.template;

import info.bliki.wiki.model.IWikiModel;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;

/**
 * A template parser function for <code>{{ #switch: ... }}</code> syntax.
 * 
 * See <a href
 * ="http://www.mediawiki.org/wiki/Help:Extension:ParserFunctions">Mediwiki's
 * Help:Extension:ParserFunctions</a>
 * 
 */
public class Switch extends AbstractTemplateFunction {
	public final static ITemplateFunction CONST = new Switch();

	public Switch() {

	}

	public String parseFunction(List<String> list, IWikiModel model, String src, int beginIndex, int endIndex) {
		if (list.size() > 2) {
			String defaultResult = null;
			String conditionString = parse(list.get(0), model);
			// StringBuilder firstBuffer = new StringBuilder(first.length());
			// TemplateParser.parse(first, model, firstBuffer, false);
			// String conditionString = first.trim();
			boolean valueFound = false;
			for (int i = 1; i < list.size(); i++) {
				String temp = parse(list.get(i), model);
				int index = temp.indexOf('=');
				String leftHandSide;
				if (index >= 0) {
					if (valueFound == true) {
						return temp.substring(index + 1).trim();
					}
					leftHandSide = temp.substring(0, index).trim();
				} else {
					leftHandSide = temp.trim();
				}
				String parsedLHS = parse(leftHandSide, model);
				if (index >= 0 && "#default".equals(parsedLHS)) {
					defaultResult = temp.substring(index + 1).trim();
					continue;
				}
				if (index < 0 && i == list.size() - 1) {
					return parsedLHS;
				}
				boolean hasDigits = false;
				
				for(int j = 0; j != conditionString.length(); j++)
					if(Character.isDigit(conditionString.charAt(j))) {
						hasDigits = true;
						break;
					}
						
				if (equalsTypes(conditionString, parsedLHS, hasDigits)) {
					if (index >= 0) {
						return temp.substring(index + 1).trim();
					} else {
						valueFound = true;
					}
				}

			}
			return defaultResult;
		}
		return null;
	}

	private boolean equalsTypes(String first, String second, boolean hasDigits) {
		

		if (first.length() == 0) {
			return second.length() == 0;
		}
		if (second.length() == 0) {
			return first.length() == 0;
		}
		
		
		
		switch(tryAsNumbers(first, second, hasDigits)) {
		case 1:
			return true;
		case 0:
			return false;
		case -1:
		default:
			return first.equals(second);
		}
	}

	/**
	 * 
	 * @param first
	 * @param second
	 * @param hasDigits
	 * @return -1, if not numbers, 0 - if not equal, 1 if equal
	 */
	private int tryAsNumbers(String first, String second, boolean hasDigits) {
		
		if (first.charAt(0) == '+') {
			first = first.substring(1);
		}
		
		if (second.charAt(0) == '+') {
			second = second.substring(1);
		}
		
		if(!NumberUtils.isNumber(first) || !NumberUtils.isNumber(second))
			return -1;
		
		try {
			double d1 = Double.parseDouble(first);
			double d2 = Double.parseDouble(second);
			if (d1 == d2) {
				return 1;
			}
		} catch (NumberFormatException e) {
			return -1;
		}
		
		return 0;
	}
}
