package info.bliki.wiki.model;

/**
 * A default wiki event listener implementation which will trigger the
 * <code>on....</code> event methods during the parsing process.
 * 
 * This listener does nothing useful, but implementing wrappers for the
 * interface methods.
 * 
 */
public class DefaultEventListener implements IEventListener {
	public final static IEventListener CONST = new DefaultEventListener();

	@Override
	public void onHeader(String src, int rawStart, int rawEnd, int level) {
	}

	@Override
	public void onHeader(String src, int startPosition, int endPosition, int rawStart, int rawEnd, int level) {
		onHeader(src, rawStart, rawEnd, level);
	}

	@Override
	public void onWikiLink(String src, int rawStart, int rawEnd, String suffix) {
	}

	@Override
	public void onTemplate(String src, int rawStart, int rawEnd) {
	}

}
