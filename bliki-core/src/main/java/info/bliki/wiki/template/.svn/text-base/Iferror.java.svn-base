package info.bliki.wiki.template;

import info.bliki.wiki.model.IWikiModel;

import java.util.List;

/**
 * A template parser function for <code>{{ #iferror: ... }}</code> syntax. See
 * <a href
 * ="http://www.mediawiki.org/wiki/Help:Extension:ParserFunctions">Mediwiki's
 * Help:Extension:ParserFunctions</a>
 * 
 */
public class Iferror extends AbstractTemplateFunction {

	public final static ITemplateFunction CONST = new Iferror();

	public Iferror() {

	}

	public String parseFunction(List<String> list, IWikiModel model, char[] src, int beginIndex, int endIndex) {
		if (list.size() > 0) {
			boolean error = false;
			String iferrorCondition = parse(list.get(0), model);
			if (iferrorCondition.length() > 0) {
				error = iferrorCondition.indexOf(" class=\"error\"") > 0;
			}
			if (error) {
				// &lt;then text&gt;
				if (list.size() >= 2) {
					return parse(list.get(1), model);
				}
				return "";
			} else {
				if (list.size() >= 3) {
					// &lt;else text&gt;
					return parse(list.get(2), model);
				}
				return iferrorCondition;
			}
		}
		return null;
	}
}
