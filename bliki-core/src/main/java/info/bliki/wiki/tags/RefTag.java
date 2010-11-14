package info.bliki.wiki.tags;

import info.bliki.htmlcleaner.TagNode;
import info.bliki.wiki.filter.Encoder;
import info.bliki.wiki.filter.ITextConverter;
import info.bliki.wiki.model.IWikiModel;
import info.bliki.wiki.tags.util.IBodyTag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Wiki tag for references &lt;ref&gt;reference text...&lt;/ref&gt;
 * 
 * See <a href="http://en.wikipedia.org/wiki/Wikipedia:Footnotes">Footnotes</a>
 */
public class RefTag extends HTMLTag implements IBodyTag {

	public RefTag() {
		super("ref");
	}

	@Override
	public void renderHTML(ITextConverter converter, Appendable writer, IWikiModel model) throws IOException {
		TagNode node = this;
		List<Object> children = node.getChildren();
		int len = children.size();
		StringBuilder buf = null;
		if (len == 0) {
			buf = new StringBuilder();
		} else {
			buf = new StringBuilder(len * 64);
		}
		renderHTMLWithoutTag(converter, buf, model);
		Map<String, String> map = getAttributes();
		String value = map.get("name");
		String[] offset = model.addToReferences(buf.toString(), value);// getBodyString
		if (null == value) {
			value = offset[0];
		}
		String ref = (null == offset[1]) ? offset[0] : offset[1];

		writer.append("<sup id=\"_ref-").append(Encoder.encodeDotUrl(ref)).append("\" class=\"reference\"><a href=\"#_note-").append(
				Encoder.encodeDotUrl(value)).append("\" title=\"\">[").append(offset[0]).append("]</a></sup>");
	}

	@Override
	public boolean isReduceTokenStack() {
		return false;
	}
}