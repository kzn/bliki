package info.bliki.wiki.template;

import info.bliki.wiki.model.IWikiModel;

import java.util.List;

/**
 * A template parser function for <code>{{lcfirst: ... }}</code> <i>first
 * character to lower case</i> syntax. See <a
 * href="http://en.wikipedia.org/wiki/Help:Variable#Formatting">Wikipedia -
 * Help:Variable#Formatting</a>
 * 
 */
public class LCFirst extends AbstractTemplateFunction {
	public final static ITemplateFunction CONST = new LCFirst();

	public LCFirst() {

	}

	public String parseFunction(List<String> list, IWikiModel model, char[] src, int beginIndex, int endIndex) {
		if (list.size() > 0) {
			String word = parse(list.get(0), model);
			if (word.length() > 0) {
				return Character.toLowerCase(word.charAt(0)) + word.substring(1);
			}
			return "";
		}
		return null;
	}
}
