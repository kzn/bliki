package info.bliki.wiki.template;

import info.bliki.wiki.model.IWikiModel;

import java.util.List;

/**
 * A template parser function for <code>{{subst: ... }}</code>. See <a
 * href="http://en.wikipedia.org/wiki/Help:Substitution"
 * >Wikipedia-Help:Substitution</a>
 * 
 */
public class Subst extends AbstractTemplateFunction {
	public final static ITemplateFunction CONST = new Subst();

	public Subst() {

	}

	public String parseFunction(List<String> parts, IWikiModel model, char[] src, int beginIndex, int endIndex) {
		StringBuilder template = new StringBuilder(endIndex - beginIndex + 4);
		template.append("{{");
		template.append(src, beginIndex, endIndex - beginIndex);
		template.append("}}");
		return parse(template.toString(), model);
	}
}
