package info.bliki.wiki.filter;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TemplateFilterTest extends FilterTestSupport {
	public TemplateFilterTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(TemplateFilterTest.class);
	}

	public void testTemplate06() {
		assertEquals("\n" + "<p>start- 5.0 equals +5 -end</p>", wikiModel.render("start- {{ifeq|5.0|+5}} -end"));
	}

	public void testTemplate09() {
		assertEquals("\n" + "<p>start- test is not equal Test -end</p>", wikiModel.render("start- {{ifeq|test|Test}} -end"));
	}

	public void testTemplateCall3() {
		// see method WikiTestModel#getRawWikiContent()
		assertEquals("\n" + "<p>b) First: Test1 Second: c) First: sdfsf Second: klj </p>\n" + "", wikiModel.render("{{templ1\n"
				+ " | a = Test1\n" + " | {{templ2|sdfsf|klj}} \n" + "}}\n" + ""));
	}

	public void testSwitch001() {
		assertEquals("\n" + "<p>{{Templ1/ind&#38;}}</p>", wikiModel.render("{{Templ1/{{ #switch: imperative  | ind | ind&}}}}"));
	}

	public void testNonExistentTemplate() {
		assertEquals("<h2><span class=\"mw-headline\" id=\"Other_areas_of_Wikipedia\">Other areas of Wikipedia</span></h2>\n" + 
				"<p>{{WikipediaOther}}</p>", wikiModel.render("==Other areas of Wikipedia==\n"
				+ "{{WikipediaOther}}<!--Template:WikipediaOther-->"));
	}

	public void testTemplateCall1() {
		// see method WikiTestModel#getRawWikiContent()
		assertEquals("\n" + "<p>start-an include page-end</p>", wikiModel.render("start-{{:Include Page}}-end"));
	}

	public void testTemplateCall2() {
		// see method WikiTestModel#getRawWikiContent()
		assertEquals("\n" + "<p>start-b) First: 3 Second: b-end start-c) First: sdfsf Second: klj-end</p>", wikiModel
				.render("start-{{templ1|a=3|b}}-end start-{{templ2|sdfsf|klj}}-end"));
	}

	public void testTemplateCall4() {
		// see method WikiTestModel#getRawWikiContent() for template tl
		assertEquals(
				"\n" + "<p>{{<a href=\"http://www.bliki.info/wiki/Template:example\" title=\"Template:example\">example</a>}}</p>",
				wikiModel.render("{{tl|example}}"));
	}

	public void testTemplateCall4a() {
		// see method WikiTestModel#getRawWikiContent() for template tl
		assertEquals("\n" + "<p>{{[[Template:{{{1}}}|{{{1}}}]]}}</p>", wikiModel.render("{{tl}}"));
	}

	public void testTemplateCall5() {
		// see method WikiTestModel#getRawWikiContent()
		assertEquals(
				"\n"
						+ "<p>(pronounced <span class=\"IPA\" title=\"Pronunciation in the International Phonetic Alphabet (IPA)\"><a href=\"http://www.bliki.info/wiki/WP:IPA_for_English\" title=\"WP:IPA for English\">/dəˌpeʃˈmoʊd/</a></span>)</p>",
				wikiModel.render("({{pron-en|dəˌpeʃˈmoʊd}})"));
	}

	public void testTemplateNowiki() {
		// see method WikiTestModel#getTemplateContent()
		assertEquals("\n" + "<p>start-{{templ1|a=3|b}}-end</p>", wikiModel.render("start-<nowiki>{{templ1|a=3|b}}-</noWiKi>end"));
	}

	public void testTemplateParameter01() {
		// see method WikiTestModel#getTemplateContent()
		assertEquals("\n" + "<p>start-a) First: arg1 Second: arg2-end</p>", wikiModel.render("start-{{Test|arg1|arg2}}-end"));
	}

	public void testTemplateParameter02() {
		// see method WikiTestModel#getTemplateContent()
		assertEquals(
				"\n"
						+ "<p>start- <i><a class=\"externallink\" href=\"http://www.etymonline.com/index.php?search=hello&#38;searchmode=none\" rel=\"nofollow\" title=\"http://www.etymonline.com/index.php?search=hello&#38;searchmode=none\">Online Etymology Dictionary</a></i>. -end</p>",
				wikiModel
						.render("start- {{cite web|url=http://www.etymonline.com/index.php?search=hello&searchmode=none|title=Online Etymology Dictionary}} -end"));
	}

	public void testTemplateParameter03() {
		// see method WikiTestModel#getTemplateContent()
		assertEquals("\n" + "<p>start- </p>\n"
				+ "<div class=\"references-small\" style=\"-moz-column-count:2; -webkit-column-count:2; column-count:2;\"></div> -end",
				wikiModel.render("start- {{reflist|2}} -end"));
	}

	public void testTemplate04() {
		assertEquals("\n" + "<p>A is not equal B</p>", wikiModel.render("{{#ifeq: A | B |A equals B |A is not equal B}}"));
	}

	public void testTemplate05() {
		assertEquals("\n" + "<p>start- A is not equal B -end</p>", wikiModel.render("start- {{ifeq|A|B}} -end"));
	}

	public void testTemplate07() {
		assertEquals("\n" + "<p>start- 5.001 is not equal +5 -end</p>", wikiModel.render("start- {{ifeq|5.001|+5}} -end"));
	}

	public void testTemplate08() {
		assertEquals("\n" + "<p>start- test equals test -end</p>", wikiModel.render("start- {{ifeq|test|test}} -end"));
	}

	public void testTemplate10() {
		assertEquals("", wikiModel.render("{{{x| }}}"));
	}

	public void testTemplate11() {
		assertEquals("\n" + "<p>{{{title}}}</p>", wikiModel.render("{{{title}}}"));
	}

	public void testTemplate12() {
		assertEquals(
				"\n"
						+ "<div style=\"page-break-inside: avoid;\">\n"
						+ "<table cellspacing=\"5\" class=\"infobox\" style=\"width: 21em; font-size: 90%; text-align: left;\">\n"
						+ "<tr>\n"
						+ "<th colspan=\"2\" style=\"text-align: center; font-size: 130%;\">JAMWiki</th></tr>\n"
						+ "<tr>\n"
						+ "<th><a href=\"http://www.bliki.info/wiki/Software_release_life_cycle\" title=\"Software release life cycle\">Latest release</a></th>\n"
						+ "<td>0.6.5 / <a href=\"http://www.bliki.info/wiki/March_16\" title=\"March 16\">March 16</a>, <a href=\"http://www.bliki.info/wiki/2008\" title=\"2008\">2008</a></td></tr>\n"
						+ "<tr>\n"
						+ "<th><a href=\"http://www.bliki.info/wiki/Software_release_life_cycle\" title=\"Software release life cycle\">Preview release</a></th>\n"
						+ "<td>0.6.5 </td></tr>\n"
						+ "<tr>\n"
						+ "<th><a href=\"http://www.bliki.info/wiki/Operating_system\" title=\"Operating system\">OS</a></th>\n"
						+ "<td><a href=\"http://www.bliki.info/wiki/Cross-platform\" title=\"Cross-platform\">Cross-platform</a></td></tr>\n"
						+ "<tr>\n"
						+ "<th><a href=\"http://www.bliki.info/wiki/List_of_software_categories\" title=\"List of software categories\">Genre</a></th>\n"
						+ "<td><a href=\"http://www.bliki.info/wiki/Wiki_software\" title=\"wiki software\">Wiki software</a></td></tr>\n"
						+ "<tr>\n"
						+ "<th><a href=\"http://www.bliki.info/wiki/Software_license\" title=\"Software license\">License</a></th>\n"
						+ "<td><a href=\"http://www.bliki.info/wiki/GNU_Lesser_General_Public_License\" title=\"GNU Lesser General Public License\">LGPL</a></td></tr>\n"
						+ "<tr>\n"
						+ "<th><a href=\"http://www.bliki.info/wiki/Website\" title=\"Website\">Website</a></th>\n"
						+ "<td><a class=\"externallink\" href=\"http://www.jamwiki.org/\" rel=\"nofollow\" title=\"http://www.jamwiki.org/\">JAMWiki wiki</a>\n"
						+ "</td></tr></table></div>\n" + "", wikiModel.render("{{Infobox_Software\n" + "|name = JAMWiki\n" + "|logo = \n"
						+ "|caption =\n" + "\n" + "|developer = \n" + "|latest_release_version = 0.6.5\n"
						+ "|latest_release_date = [[March 16]], [[2008]]\n" + "|latest preview version = 0.6.5 \n"
						+ "|latest preview date = \n" + "|operating_system = [[Cross-platform]]\n"
						+ "|genre = [[wiki software|Wiki software]]\n" + "|license = [[GNU Lesser General Public License|LGPL]]\n"
						+ "|website = [http://www.jamwiki.org/ JAMWiki wiki]\n" + "}}\n"));
	}

	public void testTemplateParameter13() {
		// see method WikiTestModel#getTemplateContent()
		assertEquals(
				"\n"
						+ "<div style=\"page-break-inside: avoid;\">\n"
						+ "<table class=\"wikitable\">\n"
						+ "<caption>Versionsgeschichte von JavaScript<sup id=\"_ref-1\" class=\"reference\"><a href=\"#_note-1\" title=\"\">[1]</a></sup></caption>\n"
						+ "<tr>\n"
						+ "<th>Version </th>\n"
						+ "<th>Release </th>\n"
						+ "<th>Entsprechung </th>\n"
						+ "<th>Netscape Navigator </th>\n"
						+ "<th>Mozilla Firefox </th>\n"
						+ "<th>Internet Explorer </th>\n"
						+ "<th>Opera </th>\n"
						+ "<th>Safari </th>\n"
						+ "<th>Google Chrome</th></tr></table></div>\n"
						+ "<pre /><ol class=\"references\">\n"
						+ "<li id=\"_note-1\"><b><a href=\"#_ref-1\" title=\"\">&#8593;</a></b> John Resig. <i><a class=\"externallink\" href=\"http://ejohn.org/blog/versions-of-javascript\" rel=\"nofollow\" title=\"http://ejohn.org/blog/versions-of-javascript\">Versions of JavaScript</a></i>. Ejohn.org. Abgerufen am {{FormatDate}}.</li></ol>",
				wikiModel
						.render("{| class=\"wikitable\"\n"
								+ "|+Versionsgeschichte von JavaScript<ref>{{cite web|author=John Resig |url=http://ejohn.org/blog/versions-of-javascript |title=Versions of JavaScript |publisher=Ejohn.org |date= |accessdate=2009-05-19}}</ref>\n"
								+ "|-\n"
								+ "! Version !! Release !! Entsprechung !! Netscape Navigator !! Mozilla Firefox !! Internet Explorer !! Opera !! Safari !! Google Chrome\n"
								+ "|}\n" + " <references/>\n"));
	}

	// private final String TEST_STRING_01 =
	// "[[Category:Interwiki templates|wikipedia]]\n" +
	// "[[zh:Template:Wikipedia]]\n"
	// + "&lt;/noinclude&gt;&lt;div class=&quot;sister-\n"
	// +
	// "wikipedia&quot;&gt;&lt;div class=&quot;sister-project&quot;&gt;&lt;div\n"
	// +
	// "class=&quot;noprint&quot; style=&quot;clear: right; border: solid #aaa\n"
	// + "1px; margin: 0 0 1em 1em; font-size: 90%; background: #f9f9f9; width:\n"
	// + "250px; padding: 4px; text-align: left; float: right;&quot;&gt;\n"
	// +
	// "&lt;div style=&quot;float: left;&quot;&gt;[[Image:Wikipedia-logo-en.png|44px|none| ]]&lt;/div&gt;\n"
	// + "&lt;div style=&quot;margin-left: 60px;&quot;&gt;{{#if:{{{lang|}}}|\n"
	// + "{{{{{lang}}}}}&amp;nbsp;}}[[Wikipedia]] has {{#if:{{{cat|\n" +
	// "{{{category|}}}}}}|a category|{{#if:{{{mul|{{{dab|\n"
	// +
	// "{{{disambiguation|}}}}}}}}}|articles|{{#if:{{{mulcat|}}}|categories|an\n"
	// + "article}}}}}} on:\n"
	// + "&lt;div style=&quot;margin-left: 10px;&quot;&gt;'''''{{#if:{{{cat|\n"
	// + "{{{category|}}}}}}|[[w:{{#if:{{{lang|}}}|{{{lang}}}:}}Category:\n"
	// + "{{ucfirst:{{{cat|{{{category}}}}}}}}|{{ucfirst:{{{1|{{{cat|\n"
	// +
	// "{{{category}}}}}}}}}}}]]|[[w:{{#if:{{{lang|}}}|{{{lang}}}:}}{{ucfirst:\n"
	// + "{{#if:{{{dab|{{{disambiguation|}}}}}}|{{{dab|{{{disambiguation}}}}}}|\n"
	// +
	// "{{{1|{{PAGENAME}}}}}}}}}|{{ucfirst:{{{2|{{{1|{{{dab|{{{disambiguation|\n"
	// + "{{PAGENAME}}}}}}}}}}}}}}}}]]}}''''' {{#if:{{{mul|{{{mulcat|}}}}}}|and\n"
	// + "'''''{{#if:{{{mulcat|}}}|[[w:{{#if:{{{lang|}}}|{{{lang}}}:}}Category:\n"
	// +
	// "{{ucfirst:{{{mulcat}}}}}|{{ucfirst:{{{mulcatlabel|{{{mulcat}}}}}}}}]]|\n"
	// + "[[w:{{#if:{{{lang|}}}|{{{lang}}}:}}{{ucfirst:{{{mul}}}}}|{{ucfirst:\n"
	// + "{{{mullabel|{{{mul}}}}}}}}]]'''''}}|}}&lt;/div&gt;\n" + "&lt;/div&gt;\n"
	// + "&lt;/div&gt;\n"
	// +
	// "&lt;/div&gt;&lt;/div&gt;&lt;span class=&quot;interProject&quot;&gt;[[w:\n"
	// + "{{#if:{{{lang|}}}|{{{lang}}}:}}{{#if:{{{cat|{{{category|}}}}}}|\n"
	// + "Category:{{ucfirst:{{{cat|{{{category}}}}}}}}|{{ucfirst:{{{dab|\n"
	// + "{{{disambiguation|{{{1|{{PAGENAME}}}}}}}}}}}}}}}|Wikipedia {{#if:\n"
	// + "{{{lang|}}}|&lt;sup&gt;{{{lang}}}&lt;/sup&gt;}}]]&lt;/span&gt;{{#if:\n"
	// +
	// "{{{mul|{{{mulcat|}}}}}}|&lt;span class=&quot;interProject&quot;&gt;[[w:\n"
	// +
	// "{{#if:{{{lang|}}}|{{{lang}}}:}}{{#if:{{{mulcat|}}}|Category:{{ucfirst:\n"
	// + "{{{mulcat}}}}}|{{ucfirst:{{{mul}}}}}}}|Wikipedia {{#if:{{{lang|}}}|\n"
	// + "&lt;sup&gt;{{{lang}}}&lt;/sup&gt;}}]]&lt;/span&gt;}}";
	//
	// public void testNestedIf01() {
	// String temp = StringEscapeUtils.unescapeHtml(TEST_STRING_01);
	// assertEquals(
	// "\n"
	// + "<p>\n"
	// +
	// "<a href=\"http://zh.wikipedia.org/wiki/Template:Wikipedia\">zh:Template:Wikipedia</a>\n"
	// + "&#60;/noinclude&#62;</p>\n"
	// + "<div class=\"sister-\n"
	// + "wikipedia\">\n"
	// + "<div class=\"sister-project\">\n"
	// + "<div class=\"noprint\" style=\"clear: right; border: solid #aaa\n"
	// + "1px; margin: 0 0 1em 1em; font-size: 90%; background: #f9f9f9; width:\n"
	// + "250px; padding: 4px; text-align: left; float: right;\">\n"
	// +
	// "<div style=\"float: left;\"><div style=\"width:44px\"><a class=\"internal\" href=\"http://www.bliki.info/wiki/Image:44px-Wikipedia-logo-en.png\" ><img src=\"http://www.bliki.info/wiki/44px-Wikipedia-logo-en.png\" class=\"location-none\" width=\"44\" />\n"
	// + "</a></div>\n"
	// + "</div>\n"
	// +
	// "<div style=\"margin-left: 60px;\"><a href=\"http://www.bliki.info/wiki/Wikipedia\" title=\"Wikipedia\">Wikipedia</a> has an\n"
	// + "<p>article on:\n"
	// + "</p>\n"
	// +
	// "<div style=\"margin-left: 10px;\"><b><i><a href=\"http://en.wikipedia.org/wiki/PAGENAME\">PAGENAME</a></i></b> </div>"
	// + "</div>" + "</div>"
	// +
	// "</div></div><span class=\"interProject\"><a href=\"http://en.wikipedia.org/wiki/PAGENAME\">Wikipedia</a></span>"
	// ,
	// wikiModel.render(temp));
	// }

	private final String TEST_STRING_02 = " {{#if:{{{cat|\n" + "{{{category|}}}}}}|a category|{{#if:{{{mul|{{{dab|\n"
			+ "{{{disambiguation|}}}}}}}}}|articles|{{#if:{{{mulcat|}}}|categories|an\n" + "article}}}}}} on:\n";

	public void testNestedIf02() {
		assertEquals("\n" + "<pre>\nan\n</pre>\n" + "<p>article on:\n" + "</p>" + "", wikiModel.render(TEST_STRING_02));
	}

	public void testParserFunctionLC001() {
		assertEquals("\n" + "<p>A lower case text</p>", wikiModel.render("A {{lc: Lower Case}} text"));
	}

	public void testParserFunctionTag001() {
		assertEquals("\n"
				+ "<p><sup id=\"_ref-1\" class=\"reference\"><a href=\"#_note-1\" title=\"\">[1]</a></sup></p><ol class=\"references\">\n"
				+ "<li id=\"_note-1\"><b><a href=\"#_ref-1\" title=\"\">&#8593;</a></b> <b>a simple test</b></li></ol>", wikiModel
				.render("{{#tag:ref|'''a simple test'''}}{{#tag:references}}"));
	}

	public final static String NAVBOX_STRING = "{{Navbox\n" + "|name  = AcademyAwardBestActor 1981-2000\n"
			+ "|title = [[Academy Award for Best Actor|Academy Award for]] [[Academy Award for Best Actor#1980s|Best Actor]]\n"
			+ "|titlestyle = background: #EEDD82\n" + "|list1 = <div>\n" + "{{nowrap|[[Henry Fonda]] (1981)}}{{·}}\n"
			+ "{{nowrap|[[Ben Kingsley]] (1982)}}{{·}}\n" + "{{nowrap|[[Robert Duvall]] (1983)}}{{·}}\n"
			+ "{{nowrap|[[F. Murray Abraham]] (1984)}}{{·}}\n" + "{{nowrap|[[William Hurt]] (1985)}}{{·}}\n"
			+ "{{nowrap|[[Paul Newman]] (1986)}}{{·}}\n" + "{{nowrap|[[Michael Douglas]] (1987)}}{{·}}\n"
			+ "{{nowrap|[[Dustin Hoffman]] (1988)}}{{·}}\n" + "{{nowrap|[[Daniel Day-Lewis]] (1989)}}{{·}}\n"
			+ "{{nowrap|[[Jeremy Irons]] (1990)}}{{·}}\n" + "{{nowrap|[[Anthony Hopkins]] (1991)}}{{·}}\n"
			+ "{{nowrap|[[Al Pacino]] (1992)}}{{·}}\n" + "{{nowrap|[[Tom Hanks]] (1993)}}{{·}}\n"
			+ "{{nowrap|[[Tom Hanks]] (1994)}}{{·}}\n" + "{{nowrap|[[Nicolas Cage]] (1995)}}{{·}}\n"
			+ "{{nowrap|[[Geoffrey Rush]] (1996)}}{{·}}\n" + "{{nowrap|[[Jack Nicholson]] (1997)}}{{·}}\n"
			+ "{{nowrap|[[Roberto Benigni]] (1998)}}{{·}}\n" + "{{nowrap|[[Kevin Spacey]] (1999)}}{{·}}\n"
			+ "{{nowrap|[[Russell Crowe]] (2000) }}\n" + "----\n"
			+ "{{nowrap|[[:Template:Academy Award Best Actor|Complete List]]}}{{·}}\n"
			+ "{{nowrap|[[:Template:AcademyAwardBestActor 1927-1940|(1928–1940)]]}}{{·}}\n"
			+ "{{nowrap|[[:Template:AcademyAwardBestActor 1941-1960|(1941–1960)]]}}{{·}}\n"
			+ "{{nowrap|[[:Template:AcademyAwardBestActor 1961-1980|(1961–1980)]]}}{{·}}\n"
			+ "{{nowrap|[[:Template:AcademyAwardBestActor 1981-2000|\'\'\'(1981–2000)\'\'\']]}}{{·}}\n"
			+ "{{nowrap|[[:Template:AcademyAwardBestActor 2001-2020|(2001-present)]]}}\n" + "</div>\n" + "}}<noinclude>\n" + "\n"
			+ "[[Category:Academy Award for Best Actor templates| 1981-2000]]\n" + "</noinclude>";

	public void testNavbox() {
		assertEquals(
				"\n"
						+ "<table cellspacing=\"0\" class=\"navbox\" style=\";\">\n"
						+ "\n"
						+ "<tr>\n"
						+ "\n"
						+ "<td style=\"padding:2px;\">\n"
						+ "<table cellspacing=\"0\" class=\"nowraplinks collapsible autocollapse \" style=\"width:100%;background:transparent;color:inherit;;\">\n"
						+ "\n"
						+ "<tr>\n"
						+ "\n"
						+ "<th class=\"navbox-title\" colspan=\"2\" style=\";background: #EEDD82\">\n"
						+ "\n"
						+ "<div style=\"float:left; width:6em;text-align:left;\">\n"
						+ "<div class=\"noprint plainlinksneverexpand\" style=\"background-color:transparent; padding:0; white-space:nowrap; font-weight:normal; font-size:xx-small; ;background: #EEDD82;border:none;;  \"><a href=\"http://www.bliki.info/wiki/Template:AcademyAwardBestActor_1981-2000\" title=\"Template:AcademyAwardBestActor 1981-2000\"><span style=\";background: #EEDD82;border:none;;\" title=\"View this template\">v</span></a> <span style=\"font-size:80%;\">•</span> <a href=\"http://www.bliki.info/wiki/Template_talk:AcademyAwardBestActor_1981-2000\" title=\"Template talk:AcademyAwardBestActor 1981-2000\"><span style=\"color:#002bb8;;background: #EEDD82;border:none;;\" title=\"Discussion about this template\">d</span></a> <span style=\"font-size:80%;\">•</span> <a class=\"externallink\" href=\"http://en.wikipedia.org/w/index.php?title=Template%3AAcademyAwardBestActor+1981-2000&#38;action=edit\" rel=\"nofollow\" title=\"http://en.wikipedia.org/w/index.php?title=Template%3AAcademyAwardBestActor+1981-2000&#38;action=edit\"><span style=\"color:#002bb8;;background: #EEDD82;border:none;;\" title=\"You can edit this template. Please use the preview button before saving.\">e</span></a></div></div><span style=\"font-size:110%;\"><a href=\"http://www.bliki.info/wiki/Academy_Award_for_Best_Actor\" title=\"Academy Award for Best Actor\">Academy Award for</a> <a href=\"http://www.bliki.info/wiki/Academy_Award_for_Best_Actor#1980s\" title=\"Academy Award for Best Actor\">Best Actor</a></span>\n"
						+ "</th>\n"
						+ "</tr>\n"
						+ "<tr style=\"height:2px;\">\n"
						+ "\n"
						+ "<td />\n"
						+ "</tr>\n"
						+ "<tr>\n"
						+ "\n"
						+ "<td class=\"navbox-list navbox-odd\" colspan=\"2\" style=\"width:100%;padding:0px;;;\">\n"
						+ "<div style=\"padding:0em 0.25em\">\n"
						+ "<div>\n"
						+ "<p><span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Henry_Fonda\" title=\"Henry Fonda\">Henry Fonda</a> (1981)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Ben_Kingsley\" title=\"Ben Kingsley\">Ben Kingsley</a> (1982)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Robert_Duvall\" title=\"Robert Duvall\">Robert Duvall</a> (1983)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/F._Murray_Abraham\" title=\"F. Murray Abraham\">F. Murray Abraham</a> (1984)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/William_Hurt\" title=\"William Hurt\">William Hurt</a> (1985)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Paul_Newman\" title=\"Paul Newman\">Paul Newman</a> (1986)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Michael_Douglas\" title=\"Michael Douglas\">Michael Douglas</a> (1987)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Dustin_Hoffman\" title=\"Dustin Hoffman\">Dustin Hoffman</a> (1988)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Daniel_Day-Lewis\" title=\"Daniel Day-Lewis\">Daniel Day-Lewis</a> (1989)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Jeremy_Irons\" title=\"Jeremy Irons\">Jeremy Irons</a> (1990)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Anthony_Hopkins\" title=\"Anthony Hopkins\">Anthony Hopkins</a> (1991)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Al_Pacino\" title=\"Al Pacino\">Al Pacino</a> (1992)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Tom_Hanks\" title=\"Tom Hanks\">Tom Hanks</a> (1993)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Tom_Hanks\" title=\"Tom Hanks\">Tom Hanks</a> (1994)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Nicolas_Cage\" title=\"Nicolas Cage\">Nicolas Cage</a> (1995)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Geoffrey_Rush\" title=\"Geoffrey Rush\">Geoffrey Rush</a> (1996)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Jack_Nicholson\" title=\"Jack Nicholson\">Jack Nicholson</a> (1997)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Roberto_Benigni\" title=\"Roberto Benigni\">Roberto Benigni</a> (1998)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Kevin_Spacey\" title=\"Kevin Spacey\">Kevin Spacey</a> (1999)</span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Russell_Crowe\" title=\"Russell Crowe\">Russell Crowe</a> (2000)</span></p><hr/>\n"
						+ "<p><span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Template:Academy_Award_Best_Actor\" title=\"Template:Academy Award Best Actor\">Complete List</a></span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Template:AcademyAwardBestActor_1927-1940\" title=\"Template:AcademyAwardBestActor 1927-1940\">(1928–1940)</a></span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Template:AcademyAwardBestActor_1941-1960\" title=\"Template:AcademyAwardBestActor 1941-1960\">(1941–1960)</a></span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Template:AcademyAwardBestActor_1961-1980\" title=\"Template:AcademyAwardBestActor 1961-1980\">(1961–1980)</a></span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Template:AcademyAwardBestActor_1981-2000\" title=\"Template:AcademyAwardBestActor 1981-2000\"><b>(1981–2000)</b></a></span><span style=\"font-weight:bold;\"> ·</span> \n"
						+ "<span style=\"white-space:nowrap;\"><a href=\"http://www.bliki.info/wiki/Template:AcademyAwardBestActor_2001-2020\" title=\"Template:AcademyAwardBestActor 2001-2020\">(2001-present)</a></span>\n"
						+ "</p></div></div></td>\n" + "</tr>\n" + "</table></td>\n" + "</tr>\n" + "</table>", wikiModel.render(NAVBOX_STRING));
	}

	public void test11() {
		assertEquals(
				"\n"
						+ "<p><a href=\"http://www.bliki.info/wiki/Template:AcademyAwardBestActor_1981-2000\" title=\"Template:AcademyAwardBestActor_1981-2000\"><span style=\";background: #EEDD82;border:none;;\" title=\"View this template\">v</span></a></p>",
				wikiModel
						.render("[[Template:AcademyAwardBestActor_1981-2000|<span title=\"View this template\" style=\";background: #EEDD82;border:none;;\">v</span>]]"));
	}

	public void testPipe001() {
		assertEquals("\n" + "<p>hello worldhello world </p>", wikiModel.render("{{2x|hello world" + "}} "));
	}

	public void testPipe002() {
		assertEquals("\n" + "<div style=\"page-break-inside: avoid;\">\n" + "<table>\n" + "<tr>\n" + "<td>A </td>\n"
				+ "<td>B</td></tr>\n" + "<tr>\n" + "<td>C</td>\n" + "<td>D</td></tr></table></div>\n" + "\n"
				+ "<div style=\"page-break-inside: avoid;\">\n" + "<table>\n" + "<tr>\n" + "<td>A </td>\n" + "<td>B</td></tr>\n" + "<tr>\n"
				+ "<td>C</td>\n" + "<td>D</td></tr></table></div>\n" + "", wikiModel.render("{{2x|{{{!}} \n" + "{{!}} A \n" + "{{!}} B\n"
				+ "{{!}}- \n" + "{{!}} C\n" + "{{!}} D\n" + "{{!}}}\n" + "}}"));
	}

	public void testPipe003() {
		assertEquals("\n" + "<div style=\"page-break-inside: avoid;\">\n" + "<table>\n" + "<tr>\n" + "<td>A </td>\n"
				+ "<td>B</td></tr>\n" + "<tr>\n" + "<td>C</td>\n" + "<td>D</td></tr></table></div>\n" + "\n"
				+ "<div style=\"page-break-inside: avoid;\">\n" + "<table>\n" + "<tr>\n" + "<td>A </td>\n" + "<td>B</td></tr>\n" + "<tr>\n"
				+ "<td>C</td>\n" + "<td>D</td></tr></table></div>\n" + "\n" + "<div style=\"page-break-inside: avoid;\">\n" + "<table>\n"
				+ "<tr>\n" + "<td>A </td>\n" + "<td>B</td></tr>\n" + "<tr>\n" + "<td>C</td>\n" + "<td>D</td></tr></table></div>\n" + "\n"
				+ "<div style=\"page-break-inside: avoid;\">\n" + "<table>\n" + "<tr>\n" + "<td>A </td>\n" + "<td>B</td></tr>\n" + "<tr>\n"
				+ "<td>C</td>\n" + "<td>D</td></tr></table></div>\n" + "", wikiModel.render("{{2x|{{2x|{{{!}} \n" + "{{!}} A \n"
				+ "{{!}} B\n" + "{{!}}- \n" + "{{!}} C\n" + "{{!}} D\n" + "{{!}}}\n" + "}}}}"));
	}

	public void testFurther() {
		assertEquals(
				"\n"
						+ "<dl>\n"
						+ "<dd><span class=\"boilerplate further\"><i>Further information: <a href=\"http://www.bliki.info/wiki/History_of_molecular_biology\" title=\"History of molecular biology\">History of molecular biology</a></i></span></dd></dl>",
				wikiModel.render("{{further|[[History of molecular biology]]}}"));
	}

	public void testInvalidNoinclude() {
		assertEquals("\n" + "<p>test123 start\n" + "test123 end</p>", wikiModel.render("test123 start<noinclude>\n" + "test123 end"));
	}

	public void testInvalidIncludeonly() {
		assertEquals("\n" + "<p>test123 start</p>", wikiModel.render("test123 start<includeonly>\n" + "test123 end"));
	}

	public void testInvalidOnlyinclude() {
		assertEquals("\n" + "<p>test123 start\n" + "test123 end</p>", wikiModel.render("test123 start<onlyinclude>\n" + "test123 end"));
	}

	public void testIf_image_test() {
		assertEquals(
				"\n"
						+ "<p>test <div style=\"width:220px;\"><a class=\"internal\" href=\"http://www.bliki.info/wiki/File:220px-test.jpg\" ><img src=\"http://www.bliki.info/wiki/220px-test.jpg\" class=\"location-none\" width=\"220\" />\n"
						+ "</a></div>\n" + " test123</p>", wikiModel.render("test {{If_image_test|  image =  test.jpg}} test123"));
	}

	public void testMONTHNAME() {
		assertEquals("\n" + "<p>test October test123</p>", wikiModel.render("test {{MONTHNAME | 10 }} test123"));
	}

	public void testMONTHNUMBER() {
		assertEquals("\n" + "<p>test 10 test123</p>", wikiModel.render("test {{MONTHNUMBER | OctoBer }} test123"));
	}

	public void testInfoboxProgrammiersprachen() {
		assertEquals(
				"\n"
						+ "<div style=\"page-break-inside: avoid;\">\n"
						+ "<table class=\"prettytable float-right\" style=\"font-size:90%; width:21em;\">\n"
						+ "<tr class=\"hintergrundfarbe6\">\n"
						+ "<th colspan=\"2\" style=\"font-size:105%; text-align: center;\"><big>JavaScript</big></th></tr>\n"
						+ "<tr valign=\"top\">\n"
						+ "<td><b><a href=\"http://www.bliki.info/wiki/Programmierparadigma\" title=\"Programmierparadigma\">Paradigmen</a>:</b>\n"
						+ " </td>\n"
						+ "<td>multiparadigmatisch</td></tr>\n"
						+ "<tr valign=\"top\">\n"
						+ "<td><b>Erscheinungsjahr:</b>\n"
						+ " </td>\n"
						+ "<td>1995</td></tr>\n"
						+ "<tr valign=\"top\">\n"
						+ "<td><b>Entwickler:</b>\n"
						+ " </td>\n"
						+ "<td><a href=\"http://www.bliki.info/wiki/Brendan_Eich\" title=\"Brendan Eich\">Brendan Eich</a></td></tr>\n"
						+ "<tr valign=\"top\">\n"
						+ "<td><b>Aktuelle <a href=\"http://www.bliki.info/wiki/Versionierung\" title=\"Versionierung\">Version</a>:</b>\n"
						+ " </td>\n"
						+ "<td style=\"white-space:nowrap;\">1.8  <small>(2008)</small></td></tr>\n"
						+ "<tr valign=\"top\">\n"
						+ "<td><b><a href=\"http://www.bliki.info/wiki/Typisierung_(Informatik)\" title=\"Typisierung (Informatik)\">Typisierung</a>:</b>\n"
						+ " </td>\n"
						+ "<td><a href=\"http://www.bliki.info/wiki/Schwache_Typisierung\" title=\"Schwache Typisierung\">schwach</a>, <a href=\"http://www.bliki.info/wiki/Dynamische_Typisierung\" title=\"Dynamische Typisierung\">dynamisch</a>, <a href=\"http://www.bliki.info/wiki/Duck_Typing\" title=\"Duck Typing\">duck</a></td></tr>\n"
						+ "<tr valign=\"top\">\n"
						+ "<td><b>wichtige <a href=\"http://www.bliki.info/wiki/Implementierung\" title=\"Implementierung\">Implementierungen</a>:</b>\n"
						+ " </td>\n"
						+ "<td><a href=\"http://www.bliki.info/wiki/SpiderMonkey\" title=\"SpiderMonkey\">SpiderMonkey</a>, <a href=\"http://www.bliki.info/wiki/Rhino\" title=\"Rhino\">Rhino</a>, <a href=\"http://www.bliki.info/wiki/SquirrelFish\" title=\"SquirrelFish\">SquirrelFish</a>, <a href=\"http://www.bliki.info/wiki/V8_(JavaScript-Engine)\" title=\"V8 (JavaScript-Engine)\">V8</a></td></tr>\n"
						+ "<tr valign=\"top\">\n"
						+ "<td><b>Einflüsse:</b>\n"
						+ " </td>\n"
						+ "<td><a href=\"http://www.bliki.info/wiki/Self_(Programmiersprache)\" title=\"Self (Programmiersprache)\">Self</a>, <a href=\"http://www.bliki.info/wiki/C_(Programmiersprache)\" title=\"C (Programmiersprache)\">C</a>, <a href=\"http://www.bliki.info/wiki/Scheme\" title=\"Scheme\">Scheme</a>, <a href=\"http://www.bliki.info/wiki/Perl_(Programmiersprache)\" title=\"Perl (Programmiersprache)\">Perl</a>, <a href=\"http://www.bliki.info/wiki/Python_(Programmiersprache)\" title=\"Python (Programmiersprache)\">Python</a>, <a href=\"http://www.bliki.info/wiki/Java_(Programmiersprache)\" title=\"Java (Programmiersprache)\">Java</a></td></tr></table></div>",
				wikiModel
						.render("{{Infobox Programmiersprache"
								+ "|Name = JavaScript"
								+ "|Beschreibung = Skriptsprache"
								+ "|Paradigma = multiparadigmatisch"
								+ "|Erscheinungsjahr = 1995"
								+ "|Entwickler = [[Brendan Eich]]"
								+ "|AktuelleVersion = 1.8"
								+ "|AktuelleVersionFreigabeDatum = 2008"
								+ "|Typisierung = [[Schwache Typisierung|schwach]], [[Dynamische Typisierung|dynamisch]], [[Duck Typing|duck]]"
								+ "|Implementierung = [[SpiderMonkey]], [[Rhino]], [[SquirrelFish]], [[V8 (JavaScript-Engine)|V8]]"
								+ "|Beeinflusst_von = [[Self (Programmiersprache)|Self]], [[C (Programmiersprache)|C]], [[Scheme]], [[Perl (Programmiersprache)|Perl]], [[Python (Programmiersprache)|Python]], [[Java (Programmiersprache)|Java]]"
								+ "}}"));
	}

	public void testProgrammiersprachen() {
		assertEquals(
				"<h3><span class=\"mw-headline\" id=\"Versionsgeschichte\">Versionsgeschichte</span></h3>\n" + 
				"\n" + 
				"<div style=\"page-break-inside: avoid;\">\n" + 
				"<table class=\"wikitable\">\n" + 
				"<caption>Versionsgeschichte von JavaScript<sup id=\"_ref-1\" class=\"reference\"><a href=\"#_note-1\" title=\"\">[1]</a></sup></caption>\n" + 
				"<tr>\n" + 
				"<th>Version </th>\n" + 
				"<th>Release </th>\n" + 
				"<th>Entsprechung </th>\n" + 
				"<th>Netscape Navigator </th>\n" + 
				"<th>Mozilla Firefox </th>\n" + 
				"<th>Internet Explorer </th>\n" + 
				"<th>Opera </th>\n" + 
				"<th>Safari </th>\n" + 
				"<th>Google Chrome </th></tr>\n" + 
				"<tr>\n" + 
				"<td>1.0 </td>\n" + 
				"<td>März 1996 </td>\n" + 
				"<td />\n" + 
				"<td>2.0 </td>\n" + 
				"<td />\n" + 
				"<td>3.0 </td>\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td /></tr>\n" + 
				"<tr>\n" + 
				"<td>1.1 </td>\n" + 
				"<td>August 1996 </td>\n" + 
				"<td />\n" + 
				"<td>3.0 </td>\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td /></tr>\n" + 
				"<tr>\n" + 
				"<td>1.2 </td>\n" + 
				"<td>Juni 1997 </td>\n" + 
				"<td />\n" + 
				"<td>4.0-4.05 </td>\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td /></tr>\n" + 
				"<tr>\n" + 
				"<td>1.3 </td>\n" + 
				"<td>Oktober 1998 </td>\n" + 
				"<td>ECMA-262 1<sup>st</sup> edition / ECMA-262 2<sup>nd</sup> edition </td>\n" + 
				"<td>4.06-4.7x </td>\n" + 
				"<td />\n" + 
				"<td>4.0 </td>\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td /></tr>\n" + 
				"<tr>\n" + 
				"<td>1.4 </td>\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td>Netscape Server </td>\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td /></tr>\n" + 
				"<tr>\n" + 
				"<td>1.5 </td>\n" + 
				"<td>November 2000 </td>\n" + 
				"<td>ECMA-262 3<sup>rd</sup> edition </td>\n" + 
				"<td>6.0 </td>\n" + 
				"<td>1.0</td>\n" + 
				"<td>\n" + 
				"\n" + 
				"<ul>\n" + 
				"<li>5.5 (JScript 5.5)</li>\n" + 
				"<li>6 (JScript 5.6)</li>\n" + 
				"<li>7 (JScript 5.7)</li>\n" + 
				"<li>8 (JScript 6)</li></ul></td>\n" + 
				"<td>\n" + 
				"\n" + 
				"<ul>\n" + 
				"<li>6.0</li>\n" + 
				"<li>7.0</li>\n" + 
				"<li>8.0</li>\n" + 
				"<li>9.0</li></ul></td>\n" + 
				"<td />\n" + 
				"<td /></tr>\n" + 
				"<tr>\n" + 
				"<td>1.6 </td>\n" + 
				"<td>November 2005 </td>\n" + 
				"<td>1.5 + Array extras + Array &#38; String generics + E4X </td>\n" + 
				"<td />\n" + 
				"<td>1.5 </td>\n" + 
				"<td />\n" + 
				"<td></td>\n" + 
				"<td>\n" + 
				"\n" + 
				"<ul>\n" + 
				"<li>3.0</li>\n" + 
				"<li>3.1</li></ul></td>\n" + 
				"<td /></tr>\n" + 
				"<tr>\n" + 
				"<td>1.7 </td>\n" + 
				"<td>Oktober 2006 </td>\n" + 
				"<td>1.6 + Pythonic generators + Iterators + let + destructuring assignments </td>\n" + 
				"<td />\n" + 
				"<td>2.0 </td>\n" + 
				"<td />\n" + 
				"<td></td>\n" + 
				"<td>\n" + 
				"\n" + 
				"<ul>\n" + 
				"<li>3.2</li>\n" + 
				"<li>4.0</li></ul></td>\n" + 
				"<td>1.0</td></tr>\n" + 
				"<tr>\n" + 
				"<td>1.8 </td>\n" + 
				"<td>Juni 2008 </td>\n" + 
				"<td>1.7 + Generator expressions + Expression closures </td>\n" + 
				"<td />\n" + 
				"<td>3.0 </td>\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td /></tr>\n" + 
				"<tr>\n" + 
				"<td>1.8.1 </td>\n" + 
				"<td />\n" + 
				"<td>1.8 + geringfügige Updates </td>\n" + 
				"<td />\n" + 
				"<td>3.5 </td>\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td /></tr>\n" + 
				"<tr>\n" + 
				"<td>1.9 </td>\n" + 
				"<td />\n" + 
				"<td>1.8.1 + ECMAScript 5 Compliance </td>\n" + 
				"<td />\n" + 
				"<td>4 </td>\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td />\n" + 
				"<td /></tr></table></div>\n" + 
				"<ol class=\"references\">\n" + 
				"<li id=\"_note-1\"><b><a href=\"#_ref-1\" title=\"\">&#8593;</a></b> John Resig. <i><a class=\"externallink\" href=\"http://ejohn.org/blog/versions-of-javascript\" rel=\"nofollow\" title=\"http://ejohn.org/blog/versions-of-javascript\">Versions of JavaScript</a></i>. Ejohn.org. Abgerufen am {{FormatDate}}.</li></ol>",
				wikiModel
						.render("=== Versionsgeschichte ===\n"
								+ "{| class=\"wikitable\"\n"
								+ "|+Versionsgeschichte von JavaScript<ref>{{cite web|author=John Resig |url=http://ejohn.org/blog/versions-of-javascript |title=Versions of JavaScript |publisher=Ejohn.org |date= |accessdate=2009-05-19}}</ref>\n"
								+ "|-\n"
								+ "! Version !! Release !! Entsprechung !! Netscape Navigator !! Mozilla Firefox !! Internet Explorer !! Opera !! Safari !! Google Chrome \n"
								+ "|-\n"
								+ "| 1.0 || März 1996 ||  || 2.0 ||  || 3.0 ||  || || \n"
								+ "|-\n"
								+ "| 1.1 || August 1996 ||  || 3.0 ||  ||  ||  || || \n"
								+ "|-\n"
								+ "| 1.2 || Juni 1997 ||  || 4.0-4.05 ||  ||  ||  || || \n"
								+ "|-\n"
								+ "| 1.3 || Oktober 1998 || ECMA-262 1<sup>st</sup> edition / ECMA-262 2<sup>nd</sup> edition || 4.06-4.7x ||  || 4.0 ||  || || \n"
								+ "|-\n"
								+ "| 1.4 ||  ||  || Netscape Server ||  ||  ||  || || \n"
								+ "|-\n"
								+ "| 1.5 || November 2000 || ECMA-262 3<sup>rd</sup> edition || 6.0 || 1.0\n"
								+ "|\n"
								+ "*5.5 (JScript 5.5)\n"
								+ "*6 (JScript 5.6)\n"
								+ "*7 (JScript 5.7)\n"
								+ "*8 (JScript 6)\n"
								+ "|\n"
								+ "*6.0\n"
								+ "*7.0\n"
								+ "*8.0\n"
								+ "*9.0\n"
								+ "| || \n"
								+ "|-\n"
								+ "| 1.6 || November 2005 || 1.5 + Array extras + Array & String generics + E4X ||  || 1.5 ||  ||\n"
								+ "|\n"
								+ "*3.0\n"
								+ "*3.1\n"
								+ "| \n"
								+ "|-\n"
								+ "| 1.7 || Oktober 2006 || 1.6 + Pythonic generators + Iterators + let + destructuring assignments ||  || 2.0 ||  ||\n"
								+ "|\n" + "* 3.2\n" + "* 4.0\n" + "| 1.0\n" + "|-\n"
								+ "| 1.8 || Juni 2008 || 1.7 + Generator expressions + Expression closures ||  || 3.0 ||  ||  || || \n" + "|-\n"
								+ "| 1.8.1 ||  || 1.8 + geringfügige Updates ||  || 3.5 ||  ||  || || \n" + "|-\n"
								+ "| 1.9 ||  || 1.8.1 + ECMAScript 5 Compliance ||  || 4 ||  ||  || || \n" + "|}\n" + "\n" + "<references/>\n"));
	}

	/**
	 * This is a date dependent template test, so only activate it for local tests
	 * please.
	 */
	// public void testbirth_date_and_age() {
	// assertEquals(
	// "\n"
	// +
	// "<p>test July 9, 1956<span style=\"display:none\"> (<span class=\"bday\">1956-07-09</span>)</span><span class=\"noprint\"> (age 54)</span> test123</p>",
	// wikiModel.render("test {{birth date and age|1956|7|9}} test123"));
	// }

}
