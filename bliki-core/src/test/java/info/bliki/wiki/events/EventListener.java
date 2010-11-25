package info.bliki.wiki.events;

import info.bliki.wiki.model.DefaultEventListener;

public class EventListener  extends DefaultEventListener {
	StringBuffer collectorBuffer = new StringBuffer();

	public EventListener() {

	}
	@Override
	public void onHeader(String src, int startPosition, int endPosition, int rawStart, int rawEnd, int level) {
		collectorBuffer.append(src, startPosition, endPosition);
		collectorBuffer.append("\n");
	}

	@Override
	public void onWikiLink(String src, int rawStart, int rawEnd, String suffix) {
	}

	public StringBuffer getCollectorBuffer() {
		return collectorBuffer;
	}
}
