package info.bliki.api.creator;

import info.bliki.api.User;
import info.bliki.wiki.filter.Encoder;
import info.bliki.wiki.impl.APIWikiModel;

import java.io.IOException;

/**
 * Test to load a page, images and templates from en.wikipedia.org and render it
 * into an HTML file. The CSS is always included in the generated HTML text
 * which blows up the size of the HTML file.
 */
public class HTMLCreatorExample {
	public HTMLCreatorExample() {
		super();
	}

	public static void testWikipediaENAPI(String title) {
		testWikipediaENAPI(title, "http://en.wikipedia.org/w/api.php");
	}

	public static void testWikipediaENAPI(String title, String apiLink) {
		String[] listOfTitleStrings = { title };
		String titleURL = Encoder.encodeTitleLocalUrl(title);
		User user = new User("", "", apiLink);
		user.login();
		String mainDirectory = "c:/temp/";
		// the following subdirectory should not exist if you would like to create a
		// new database
		String databaseSubdirectory = "WikiDB";
		// the following directory must exist for image downloads
		String imageDirectory = "c:/temp/WikiImages";
		// the generated HTML will be stored in this file name:
		String generatedHTMLFilename = mainDirectory + titleURL + ".html";

		WikiDB db = null;

		try {
			db = new WikiDB(mainDirectory, databaseSubdirectory);
			APIWikiModel wikiModel = new APIWikiModel(user, db, "${image}", "${title}", imageDirectory);
			DocumentCreator creator = new DocumentCreator(wikiModel, user, listOfTitleStrings);
			// create header and CSS information
			creator.setHeader(HTMLConstants.HTML_HEADER1 + HTMLConstants.CSS_MAIN_STYLE + HTMLConstants.CSS_SCREEN_STYLE
					+ HTMLConstants.HTML_HEADER2);
			creator.setFooter(HTMLConstants.HTML_FOOTER);
			wikiModel.setUp();
			creator.renderToFile(generatedHTMLFilename);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (db != null) {
				try {
					db.tearDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void testCreator001() {
		testWikipediaENAPI("Tom Hanks");
	}

	public static void testCreator002() {
		testWikipediaENAPI("Political party strength in California");
	}

	public static void testCreator003() {
		testWikipediaENAPI("Chris Capuano");
	}

	public static void testCreator004() {
		testWikipediaENAPI("Protein");
	}

	public static void testCreator005() {
		testWikipediaENAPI("Depeche Mode");
	}

	public static void testCreator006() {
		testWikipediaENAPI("Anarchism");
	}

	public static void testCreator007() {
		testWikipediaENAPI("JavaScript", "http://de.wikipedia.org/w/api.php");
	}

	public static void testCreator008() {
		testWikipediaENAPI("libero", "http://en.wiktionary.org/w/api.php");
	}

	public static void main(String[] args) {
		testCreator001();
	}
}
