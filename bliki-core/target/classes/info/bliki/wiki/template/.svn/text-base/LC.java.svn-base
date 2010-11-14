package info.bliki.wiki.template;

import info.bliki.wiki.model.IWikiModel;

import java.util.List;

/**
 * A template parser function for <code>{{lc: ... }}</code> <i>lower case</i>
 * syntax. See <a
 * href="http://en.wikipedia.org/wiki/Help:Variable#Formatting">Wikipedia -
 * Help:Variable#Formatting</a>
 * 
 */
public class LC extends AbstractTemplateFunction {
	public final static ITemplateFunction CONST = new LC();

	public LC() {

	}

	public String parseFunction(List<String> list, IWikiModel model, char[] src, int beginIndex, int endIndex) {
		if (list.size() > 0) {
			String result = parse(list.get(0), model);
			return result.toLowerCase();
		}
		return null;
	}
}
