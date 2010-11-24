package info.bliki.wiki.filter;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

public class WikipediaScannerTest extends TestCase {
	
	
	WikipediaScanner makeScanner(String s) {
		return new WikipediaScanner(s);
	}
	
	@Test
	public void testIndexOf() throws Exception {
		WikipediaScanner s = makeScanner("aabb");
		assertEquals(2, s.indexOf('b', 'd'));
		s.setPosition(0);
		assertEquals(2, s.indexOf('b'));
		s.setPosition(0);
		assertEquals(-1, s.indexOf('d'));
		
		s = makeScanner("aadbb");
		assertEquals(-1, s.indexOf('b', 'd'));
	}
	
	@Test
	public void testIndexNoWiki() throws Exception {
		WikipediaScanner s = makeScanner("this </nowiki> test");
		assertEquals(14, s.indexEndOfNowiki());
		assertEquals(-1, s.indexEndOfNowiki());
	}
	
	@Test
	public void testIndexEndComment() throws Exception {
		WikipediaScanner s = makeScanner("this --> test");
		assertEquals(8, s.indexEndOfComment());
		assertEquals(-1, s.indexEndOfComment());
	}

}
