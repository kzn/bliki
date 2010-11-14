package info.bliki.wiki.filter;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.lang.StringEscapeUtils;

public class TemplateParserTest extends FilterTestSupport {

	public TemplateParserTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(TemplateParserTest.class);
	}

	private final String TEST_STRING_03 = "{{{1|{{PAGENAME}}}}}";

	public void testIf00() {
		assertEquals(
				"start{{es-verb form of/indicative}}end",
				wikiModel
						.parseTemplates("start{{{{ #if:  | l | u }}cfirst:  {{ es-verb form of/{{ #switch: indicative  | ind | indicative = indicative  | subj | subjunctive = subjunctive  | imp | imperative = imperative  | cond | conditional = conditional  | par | part | participle | past participle  | past-participle = participle  | adv | adverbial | ger | gerund | gerundive  | gerundio | present participle  | present-participle = adverbial  | error  }}  | tense =  {{ #switch: present  | pres | present = present  | imp | imperfect = imperfect  | pret | preterit | preterite = preterite  | fut | future = future  | cond | conditional = conditional  }}  | number =  {{ #switch: singular  | s | sg | sing | singular = singular  | p | pl | plural = plural  }}  | person =  {{ #switch: 1  | 1 | first | first person | first-person = first  | 2 | second|second person | second-person = second  | 3 | third | third person | third-person = third  | 0 | - | imp | impersonal = impersonal  }}  | formal =  {{ #switch: {{{formal}}}  | y | yes = yes  | n | no = no  }}  | gender =  {{ #switch:   | m | masc | masculine = masculine  | f | fem | feminine = feminine  }}  | sense =  {{ #switch: {{{sense}}}  | + | aff | affirmative = affirmative  | - | neg | negative = negative  }}  | sera = {{ #switch: {{{sera}}} | se = se | ra = ra }}  | ending =  {{ #switch: ar  | ar | -ar = -ar  | er | -er = -er  | ir | -ir = -ir  }}  | participle =   | voseo = {{ #if:  | yes | no }}  }}}}end"));
	}
	
	public void testIf01() {
		assertEquals("startnoend", wikiModel.parseTemplates("start{{#if:\n" + "\n" + "\n" + "| yes | no}}end"));
	}
	
	public void testIf02() {
		assertEquals("startend", wikiModel.parseTemplates("start{{      #if:   \n    |{{#ifeq:{{{seperator}}}|;|;|. }}     }}end"));
	}

	public void testIf03() {
		assertEquals("startyesend", wikiModel.parseTemplates("start{{#if:string\n" + "\n" + "\n" + "| yes | no}}end"));
	}

	public void testIf04() {
		assertEquals("startend", wikiModel.parseTemplates("start{{#if: | yes }}end"));
	}

	public void testIfeq01() {
		assertEquals("startyesend", wikiModel.parseTemplates("start{{#ifeq: 01 | 1 | yes | no}}end"));
	}

	public void testIfeq02() {
		assertEquals("startyesend", wikiModel.parseTemplates("start{{#ifeq: 0 | -0 | yes | no}}end"));
	}

	public void testIfeq03() {
		assertEquals("startnoend", wikiModel.parseTemplates("start{{#ifeq: \"01\" | \"1\" | yes | no}}end"));
	}

	public void testIf05() {
		assertEquals("startend", wikiModel.parseTemplates("start{{#if: foo | | no}}end"));
	}

	public void testIfexpr01() {
		assertEquals("startnoend", wikiModel.parseTemplates("start{{#ifexpr: 1 < 0 | | no}}end"));
	}

	public void testIfexpr02() {
		assertEquals("startend", wikiModel.parseTemplates("start{{#ifexpr: 1 > 0 | | no}}end"));
	}

	public void testBORN_DATA() {
		assertEquals(
				"test Thomas Jeffrey Hanks<br />[[Concord, California]],  [[United States|U.S.]] test123",
				wikiModel
						.parseTemplates("test {{Born_data | birthname = Thomas Jeffrey Hanks | birthplace = [[Concord, California]],  [[United States|U.S.]] }} test123"));
	}

	public void testMONTHNUMBER() {
		assertEquals("test 10 test123", wikiModel.parseTemplates("test {{MONTHNUMBER | 10 }} test123"));
	}

	public void testMONTHNAME() {
		assertEquals("test October test123", wikiModel.parseTemplates("test {{MONTHNAME | 10 }} test123"));
	}

	public void testAnarchismSidebar() {
		assertEquals("{{Sidebar}}\n" + "{{end sidebar page}}\n" + "\n" + "", wikiModel.parseTemplates("{{Anarchism sidebar}}", false));
	}

	public void testNonExistentTemplate() {
		assertEquals("==Other areas of Wikipedia==\n" + "{{WikipediaOther}}", wikiModel.parseTemplates("==Other areas of Wikipedia==\n"
				+ "{{WikipediaOther}}<!--Template:WikipediaOther-->", false));
	}

	public void testTemplateCall1() {
		// see method WikiTestModel#getRawWikiContent()
		assertEquals("start-an include page-end", wikiModel.parseTemplates("start-{{:Include Page}}-end", false));
	}

	public void testTemplateCall2() {
		// see method WikiTestModel#getRawWikiContent()
		assertEquals("start-b) First: 3 Second: b-end start-c) First: sdfsf Second: klj-end", wikiModel.parseTemplates(
				"start-{{templ1|a=3|b}}-end start-{{templ2|sdfsf|klj}}-end", false));
	}

	public void testTemplateCall3() {
		// see method WikiTestModel#getRawWikiContent()
		assertEquals("b) First: Test1 Second: c) First: sdfsf Second: klj \n" + "\n" + "", wikiModel.parseTemplates("{{templ1\n"
				+ " | a = Test1\n" + " |{{templ2|sdfsf|klj}} \n" + "}}\n" + "", false));
	}

	public void testTemplateCall4() {
		// see method WikiTestModel#getRawWikiContent()
		assertEquals("{{[[Template:example|example]]}}", wikiModel.parseTemplates("{{tl|example}}", false));
	}

	public void testTemplateCall5() {
		// see method WikiTestModel#getRawWikiContent()
		assertEquals(
				"(pronounced <span title=\"Pronunciation in the International Phonetic Alphabet (IPA)\" class=\"IPA\">[[WP:IPA for English|/dəˌpeʃˈmoʊd/]]</span>)",
				wikiModel.parseTemplates("({{pron-en|dəˌpeʃˈmoʊd}})", false));
	}

	public void testTemplateParameter01() {
		// see method WikiTestModel#getTemplateContent()
		assertEquals("start-a) First: arg1 Second: arg2-end", wikiModel.parseTemplates("start-{{Test|arg1|arg2}}-end", false));
	}

	public void testTemplateParameter02() {
		// see method WikiTestModel#getTemplateContent()
		assertEquals(
				"start- ''[http://www.etymonline.com/index.php?search=hello&searchmode=none Online Etymology Dictionary]''. -end",
				wikiModel
						.parseTemplates(
								"start- {{cite web|url=http://www.etymonline.com/index.php?search=hello&searchmode=none|title=Online Etymology Dictionary}} -end",
								false));
	}

	public void testTemplateParameter03() {
		// see method WikiTestModel#getTemplateContent()
		assertEquals("start- <div class=\"references-small\" style=\"-moz-column-count:2; -webkit-column-count:2; column-count:2;\">\n"
				+ "<references /></div> -end", wikiModel.parseTemplates("start- {{reflist|2}} -end", false));
	}

	public void testTemplateParameter04() {
		// see method WikiTestModel#getTemplateContent()
		assertEquals("start-<nowiki>{{Test|arg1|arg2}}-</noWiKi>end", wikiModel.parseTemplates(
				"start-<nowiki>{{Test|arg1|arg2}}-</noWiKi>end", false));
	}

	public void testTemplateParameter05() {
		// see method WikiTestModel#getTemplateContent()
		assertEquals("start- end", wikiModel.parseTemplates("start- <!-- {{Test|arg1|arg2}} \n --->end", false));
	}

	// 
	public void testTemplate06() {
		assertEquals("A is not equal B", wikiModel.parseTemplates("{{#ifeq: A | B | A equals B | A is not equal B}}", false));
	}

	public void testTemplate07() {
		assertEquals("start- A is not equal B \n" + " end", wikiModel.parseTemplates("start- {{ifeq|A|B}} \n end", false));
	}

	public void testNestedTemplate() {
		assertEquals("test a a nested template text template", wikiModel.parseTemplates("{{nested tempplate test}}", false));
	}

	public void testEndlessRecursion() {
		assertEquals("{{Error - recursion limit exceeded parsing templates.}}", wikiModel.parseTemplates("{{recursion}}", false));
	}

	private final String TEST_STRING_01 = "[[Category:Interwiki templates|wikipedia]]\n" + "[[zh:Template:Wikipedia]]\n"
			+ "&lt;/noinclude&gt;&lt;div class=&quot;sister-\n"
			+ "wikipedia&quot;&gt;&lt;div class=&quot;sister-project&quot;&gt;&lt;div\n"
			+ "class=&quot;noprint&quot; style=&quot;clear: right; border: solid #aaa\n"
			+ "1px; margin: 0 0 1em 1em; font-size: 90%; background: #f9f9f9; width:\n"
			+ "250px; padding: 4px; text-align: left; float: right;&quot;&gt;\n"
			+ "&lt;div style=&quot;float: left;&quot;&gt;[[Image:Wikipedia-logo-\n" + "en.png|44px|none| ]]&lt;/div&gt;\n"
			+ "&lt;div style=&quot;margin-left: 60px;&quot;&gt;{{#if:{{{lang|}}}|\n"
			+ "{{{{{lang}}}}}&amp;nbsp;}}[[Wikipedia]] has {{#if:{{{cat|\n" + "{{{category|}}}}}}|a category|{{#if:{{{mul|{{{dab|\n"
			+ "{{{disambiguation|}}}}}}}}}|articles|{{#if:{{{mulcat|}}}|categories|an\n" + "article}}}}}} on:\n"
			+ "&lt;div style=&quot;margin-left: 10px;&quot;&gt;'''''{{#if:{{{cat|\n"
			+ "{{{category|}}}}}}|[[w:{{#if:{{{lang|}}}|{{{lang}}}:}}Category:\n"
			+ "{{ucfirst:{{{cat|{{{category}}}}}}}}|{{ucfirst:{{{1|{{{cat|\n"
			+ "{{{category}}}}}}}}}}}]]|[[w:{{#if:{{{lang|}}}|{{{lang}}}:}}{{ucfirst:\n"
			+ "{{#if:{{{dab|{{{disambiguation|}}}}}}|{{{dab|{{{disambiguation}}}}}}|\n"
			+ "{{{1|{{PAGENAME}}}}}}}}}|{{ucfirst:{{{2|{{{1|{{{dab|{{{disambiguation|\n"
			+ "{{PAGENAME}}}}}}}}}}}}}}}}]]}}''''' {{#if:{{{mul|{{{mulcat|}}}}}}|and\n"
			+ "'''''{{#if:{{{mulcat|}}}|[[w:{{#if:{{{lang|}}}|{{{lang}}}:}}Category:\n"
			+ "{{ucfirst:{{{mulcat}}}}}|{{ucfirst:{{{mulcatlabel|{{{mulcat}}}}}}}}]]|\n"
			+ "[[w:{{#if:{{{lang|}}}|{{{lang}}}:}}{{ucfirst:{{{mul}}}}}|{{ucfirst:\n"
			+ "{{{mullabel|{{{mul}}}}}}}}]]'''''}}|}}&lt;/div&gt;\n" + "&lt;/div&gt;\n" + "&lt;/div&gt;\n"
			+ "&lt;/div&gt;&lt;/div&gt;&lt;span class=&quot;interProject&quot;&gt;[[w:\n"
			+ "{{#if:{{{lang|}}}|{{{lang}}}:}}{{#if:{{{cat|{{{category|}}}}}}|\n"
			+ "Category:{{ucfirst:{{{cat|{{{category}}}}}}}}|{{ucfirst:{{{dab|\n"
			+ "{{{disambiguation|{{{1|{{PAGENAME}}}}}}}}}}}}}}}|Wikipedia {{#if:\n"
			+ "{{{lang|}}}|&lt;sup&gt;{{{lang}}}&lt;/sup&gt;}}]]&lt;/span&gt;{{#if:\n"
			+ "{{{mul|{{{mulcat|}}}}}}|&lt;span class=&quot;interProject&quot;&gt;[[w:\n"
			+ "{{#if:{{{lang|}}}|{{{lang}}}:}}{{#if:{{{mulcat|}}}|Category:{{ucfirst:\n"
			+ "{{{mulcat}}}}}|{{ucfirst:{{{mul}}}}}}}|Wikipedia {{#if:{{{lang|}}}|\n"
			+ "&lt;sup&gt;{{{lang}}}&lt;/sup&gt;}}]]&lt;/span&gt;}}";

	public void testNestedIf01() {
		String temp = StringEscapeUtils.unescapeHtml(TEST_STRING_01);
		assertEquals("[[Category:Interwiki templates|wikipedia]]\n" + "[[zh:Template:Wikipedia]]\n"
				+ "</noinclude><div class=\"sister-\n" + "wikipedia\"><div class=\"sister-project\"><div\n"
				+ "class=\"noprint\" style=\"clear: right; border: solid #aaa\n"
				+ "1px; margin: 0 0 1em 1em; font-size: 90%; background: #f9f9f9; width:\n"
				+ "250px; padding: 4px; text-align: left; float: right;\">\n" + "<div style=\"float: left;\">[[Image:Wikipedia-logo-\n"
				+ "en.png|44px|none| ]]</div>\n" + "<div style=\"margin-left: 60px;\">[[Wikipedia]] has an\n" + "article on:\n"
				+ "<div style=\"margin-left: 10px;\">\'\'\'\'\'[[w:PAGENAME|PAGENAME]]\'\'\'\'\' </div>\n" + "</div>\n" + "</div>\n"
				+ "</div></div><span class=\"interProject\">[[w:\n" + "PAGENAME|Wikipedia ]]</span>", wikiModel.parseTemplates(temp, false));
	}

	private final String TEST_STRING_02 = " {{#if:{{{cat|\n" + "{{{category|}}}}}}|a category|{{#if:{{{mul|{{{dab|\n"
			+ "{{{disambiguation|}}}}}}}}}|articles|{{#if:{{{mulcat|}}}|categories|an\n" + "article}}}}}} on:\n";

	public void testNestedIf02() {
		assertEquals(" an\n" + "article on:\n" + "", wikiModel.parseTemplates(TEST_STRING_02, false));
	}

	public void testNestedIf03() {
		assertEquals("PAGENAME", wikiModel.parseTemplates(TEST_STRING_03, false));
	}
	
	private final String TEST_STRING_04 = "{{ucfirst:{{{cat|{{{category}}}}}}}}";
	
	public void testNestedIf04() {
		assertEquals("{{{category}}}", wikiModel.parseTemplates(TEST_STRING_04, false));
	}//

	public void testSwitch001() {
		assertEquals("UPPER", wikiModel.parseTemplates("{{ #switch: A | a=lower | A=UPPER  }}", false));
	}

	public void testSwitch002() {
		assertEquals("lower", wikiModel.parseTemplates("{{ #switch: {{lc:A}} | a=lower | UPPER  }}", false));
	}

	public void testSwitch003() {
		assertEquals("'''''abc''' or '''ABC'''''", wikiModel.parseTemplates("{{#switch: {{lc: {{{1| B }}} }}\n" + "| a\n" + "| b\n"
				+ "| c = '''''abc''' or '''ABC'''''\n" + "| A\n" + "| B\n" + "| C = ''Memory corruption due to cosmic rays''\n"
				+ "| #default = N/A\n" + "}}", false));
	}

	public void testSwitch004() {
		assertEquals("Yes", wikiModel.parseTemplates("{{ #switch: +07 | 7 = Yes | 007 = Bond | No  }}", false));
	}

	public void testSwitch005() {
		assertEquals("Nothing", wikiModel.parseTemplates("{{#switch: | = Nothing | foo = Foo | Something }}", false));
	}

	public void testSwitch006() {
		assertEquals("Something", wikiModel.parseTemplates("{{#switch: test | = Nothing | foo = Foo | Something }}", false));
	}

	public void testSwitch007() {
		assertEquals("Bar", wikiModel.parseTemplates("{{#switch: test | foo = Foo | #default = Bar | baz = Baz }}", false));
	}

	public void testSwitch008() {
		assertEquals("{{Templ1/ind&}}", wikiModel.parseTemplates("{{Templ1/{{ #switch: imperative  | ind | ind&}}}}", false));
	}

	public void testExpr001() {
		assertEquals("1.0E-6", wikiModel.parseTemplates("{{ #expr: 0.000001 }}", false));
	}

	public void testExpr002() {
		assertEquals("210", wikiModel.parseTemplates("{{ #expr: +30 * +7 }}", false));
	}

	public void testExpr003() {
		assertEquals("210", wikiModel.parseTemplates("{{ #expr: -30 * -7 }}", false));
	}

	public void testExpr004() {
		assertEquals("210", wikiModel.parseTemplates("{{ #expr: 30 * 7 }}", false));
	}

	public void testExpr005() {
		assertEquals("4.285714285714286", wikiModel.parseTemplates("{{ #expr: 30 / 7 }}", false));
		// assertEquals("4.285714285714286", wikiModel.parseTemplates("{{ #expr: 30
		// div 7 }}", false));
	}

	public void testExpr006() {
		assertEquals("37", wikiModel.parseTemplates("{{ #expr: 30 + 7 }}", false));
	}

	public void testExpr007() {
		assertEquals("23", wikiModel.parseTemplates("{{ #expr: 30 - 7 }}", false));
	}

	public void testExpr008() {
		assertEquals("19", wikiModel.parseTemplates("{{ #expr: 30 - 7 - 4}}", false));
	}

	public void testExpr009() {
		assertEquals("4.2857", wikiModel.parseTemplates("{{ #expr: 30 / 7 round 4}}", false));
	}

	public void testExpr010() {
		assertEquals("1", wikiModel.parseTemplates("{{ #expr: 30 <> 7}}", false));
		assertEquals("1", wikiModel.parseTemplates("{{ #expr: 30 != 7}}", false));
	}

	public void testExpr011() {
		assertEquals("0", wikiModel.parseTemplates("{{ #expr: 30 < 7}}", false));
		assertEquals("1", wikiModel.parseTemplates("{{ #expr: 30 <= 42}}", false));
	}

	public void testExpr012() {
		assertEquals("259", wikiModel.parseTemplates("{{ #expr: (30 + 7)*7 }}", false));
		assertEquals("79", wikiModel.parseTemplates("{{ #expr: 30 + 7 * 7 }}", false));
	}

	public void testExpr013() {
		assertEquals("0", wikiModel.parseTemplates("{{ #expr: 4 < 5 and 4 mod 2 }}", false));
		assertEquals("1", wikiModel.parseTemplates("{{ #expr: 4 < 5 or 4 mod 2 }}", false));
	}

	public void testExpr014() {
		assertEquals("7", wikiModel.parseTemplates("{{ #expr: not 0 * 7 }}", false));
		assertEquals("7", wikiModel.parseTemplates("{{ #expr: not 30 + 7 }}", false));
	}

	public void testFormatnum001() {
		assertEquals("1401", wikiModel.parseTemplates("{{formatnum:1401}}", false)); 
	}
	
	public void testPlural001() {
		assertEquals("is", wikiModel.parseTemplates("{{plural:n|is|are}}", false)); 
		assertEquals("is", wikiModel.parseTemplates("{{plural:0|is|are}}", false)); 
		assertEquals("is", wikiModel.parseTemplates("{{plural:1|is|are}}", false)); 
		assertEquals("are", wikiModel.parseTemplates("{{plural:2|is|are}}", false)); 
		assertEquals("are", wikiModel.parseTemplates("{{plural:3|is|are}}", false)); 
		assertEquals("are", wikiModel.parseTemplates("{{plural:{{#expr:30+7}}|is|are}}", false)); 
	}
	
	public void testExpr015() {
		assertEquals("1", wikiModel.parseTemplates("{{ #expr: trunc1.2}}", false));
		assertEquals("-1", wikiModel.parseTemplates("{{ #expr: trunc-1.2 }}", false));
		assertEquals("1", wikiModel.parseTemplates("{{ #expr: floor 1.2}}", false));
		assertEquals("-2", wikiModel.parseTemplates("{{ #expr: floor -1.2 }}", false));
		assertEquals("-2", wikiModel.parseTemplates("{{ #expr: fLoOr -1.2 }}", false));
		assertEquals("2", wikiModel.parseTemplates("{{ #expr: ceil 1.2}}", false));
		assertEquals("-1", wikiModel.parseTemplates("{{ #expr: ceil-1.2 }}", false));
	}

	public void testExpr016() {
		assertEquals("1.0E-7", wikiModel.parseTemplates("{{#expr:1.0E-7}}", false));
	}

	public void testExpr017() {
		assertEquals("<div class=\"error\">Expression error: Division by zero</div>", wikiModel.parseTemplates("{{#expr:4/0}}", false));
		assertEquals("0.75", wikiModel.parseTemplates("{{#expr:3/4}}", false));
		assertEquals("<div class=\"error\">Expression error: Division by zero</div>", wikiModel.parseTemplates("{{#expr:13 mod 0}}",
				false));
		assertEquals("1", wikiModel.parseTemplates("{{#expr:13 mod 3}}", false));
		assertEquals("27", wikiModel.parseTemplates("{{#expr:3 ^3}}", false));
		assertEquals("0.037037037037037035", wikiModel.parseTemplates("{{#expr:3 ^ (-3)}}", false));
		assertEquals("NAN", wikiModel.parseTemplates("{{#expr:(-4) ^ (-1/2)}}", false));
		assertEquals("1", wikiModel.parseTemplates("{{#expr:ln EXp 1 }}", false));
		assertEquals("2.7182818284590455", wikiModel.parseTemplates("{{#expr:exp ln e }}", false));
		assertEquals("1", wikiModel.parseTemplates("{{#expr:sin (pi/2) }}", false));
		assertEquals("6.123233995736766E-17", wikiModel.parseTemplates("{{#expr:(sin pi)/2 }}", false));
		assertEquals("6.123233995736766E-17", wikiModel.parseTemplates("{{#expr:sin pi/2 }}", false));
	}

	public void testNS001() {
		assertEquals("User_talk", wikiModel.parseTemplates("{{ns:3}}", false));
		assertEquals("Help_talk", wikiModel.parseTemplates("{{ns:{{ns:12}}_talk}}", false));
		assertEquals("MediaWiki_talk", wikiModel.parseTemplates("{{ns:{{ns:8}}_talk}}", false));
		assertEquals("MediaWiki_talk", wikiModel.parseTemplates("{{ns:{{ns:8}} talk}}", false));
		assertEquals("MediaWiki_talk", wikiModel.parseTemplates("{{ns:{{ns:8}} talk  }}", false));
		assertEquals("[[:Template:Ns:MediaWikitalk]]", wikiModel.parseTemplates("{{ns:{{ns:8}}talk}}", false));
	}

	public void testURLEncode001() {
		assertEquals("%22%23%24%25%26%27%28%29*%2C%3B%3F%5B%5D%5E%60%7B%7D", wikiModel.parseTemplates(
				"{{urlencode: \"#$%&'()*,;?[]^`{}}}", false));
		assertEquals("%3C", wikiModel.parseTemplates("{{urlencode:<}}", false));
		assertEquals("%3E", wikiModel.parseTemplates("{{urlencode:>}}", false));
		assertEquals("%7C", wikiModel.parseTemplates("{{urlencode:{{!}}}}", false));
	}

	public void testPadleft001() {
		assertEquals("8", wikiModel.parseTemplates("{{padleft:8}}", false));
		assertEquals("008", wikiModel.parseTemplates("{{padleft:8|3}}", false));
		assertEquals("8", wikiModel.parseTemplates("{{padleft:8|a}}", false));
		assertEquals("007", wikiModel.parseTemplates("{{padleft:7|3|0}}", false));
		assertEquals("000", wikiModel.parseTemplates("{{padleft:0|3|0}}", false));
		assertEquals("aaabcd", wikiModel.parseTemplates("{{padleft:bcd|6|a}}", false));
		assertEquals("----cafe", wikiModel.parseTemplates("{{padleft:cafe|8|-}}", false));
		assertEquals("|||bcd", wikiModel.parseTemplates("{{padleft:bcd|6|{{!}}}}", false));
	}

	public void testPadright001() {
		assertEquals("8", wikiModel.parseTemplates("{{padright:8}}", false));
		assertEquals("800", wikiModel.parseTemplates("{{padright:8|3}}", false));
		assertEquals("8", wikiModel.parseTemplates("{{padright:8|a}}", false));
		assertEquals("bcdaaa", wikiModel.parseTemplates("{{padright:bcd|6|a}}", false));
		assertEquals("0aaaaa", wikiModel.parseTemplates("{{padright:0|6|a}}", false));

	}

	public void testTime001() {
		// seconds since January 1970
		String currentSecondsStr = wikiModel.parseTemplates("{{ #time: U }}", false);
		Long currentSeconds = Long.valueOf(currentSecondsStr);
		assertTrue(currentSeconds > 1212598361);
	}

	public void testTag001() {
		assertEquals("<references =\"\"></references>", wikiModel.parseTemplates("{{#tag:references|{{{refs|}}}|group={{{group|}}}}}"));
	}

	/**
	 * See <a
	 * href="http://en.wikipedia.org/wiki/Help:Substitution">Wikipedia-Help:
	 * Substitution</a>
	 */
	public void testSubst001() {
		assertEquals("{{}}", wikiModel.parseTemplates("{{subst:}}", false));
		assertEquals("a nested template text", wikiModel.parseTemplates("{{subst:Nested}}", false));
	}

	/**
	 * See <a
	 * href="http://en.wikipedia.org/wiki/Help:Substitution">Wikipedia-Help:
	 * Substitution</a>
	 */
	public void testSubst002() {
		assertEquals("Image", wikiModel.parseTemplates("{{subst:ns:{{subst:#expr:2*3}}}}", false));
	}

	/**
	 * See <a
	 * href="http://en.wikipedia.org/wiki/Help:Substitution">Wikipedia-Help:
	 * Substitution</a>
	 */
	public void testSubst003() {
		assertEquals("Image", wikiModel.parseTemplates("{{ns:{{subst:#expr:2*3}}}}", false));
	}

	/**
	 * See <a
	 * href="http://en.wikipedia.org/wiki/Help:Substitution">Wikipedia-Help:
	 * Substitution</a>
	 */
	public void testSubst004() {
		assertEquals("1.0e-5", wikiModel.parseTemplates("{{subst:LC:{{subst:#expr:1/100000}}}}", false));
	}

	/**
	 * See <a
	 * href="http://en.wikipedia.org/wiki/Help:Substitution">Wikipedia-Help:
	 * Substitution</a>
	 */
	public void testSubst005() {
		assertEquals("IN", wikiModel.parseTemplates("{{subst:UC:{{subst:tc}}}}", false));
	}

	public void testSubst006() {
		assertEquals("NAMESPACE", wikiModel.parseTemplates(
				"{{subst:tl|{{subst:NAMESPACE}}}}", false));
	}

	public void testPipe001() {
		assertEquals("hello worldhello world ", wikiModel.parseTemplates("{{2x|hello world" + "}} ", false));
	}

	public void testPipe002() {
		assertEquals("{| \n" + "| A \n" + "| B\n" + "|- \n" + "| C\n" + "| D\n" + "|}\n" + "{| \n" + "| A \n" + "| B\n" + "|- \n"
				+ "| C\n" + "| D\n" + "|}\n", wikiModel.parseTemplates("{{2x|{{{!}} \n" + "{{!}} A \n" + "{{!}} B\n" + "{{!}}- \n"
				+ "{{!}} C\n" + "{{!}} D\n" + "{{!}}}\n" + "}}", false));
	}

	public void testPipe003() {
		assertEquals("{| \n" + "| A \n" + "| B\n" + "|- \n" + "| C\n" + "| D\n" + "|}\n" + "{| \n" + "| A \n" + "| B\n" + "|- \n"
				+ "| C\n" + "| D\n" + "|}\n" + "{| \n" + "| A \n" + "| B\n" + "|- \n" + "| C\n" + "| D\n" + "|}\n" + "{| \n" + "| A \n"
				+ "| B\n" + "|- \n" + "| C\n" + "| D\n" + "|}\n" + "", wikiModel.parseTemplates("{{2x|{{2x|{{{!}} \n" + "{{!}} A \n"
				+ "{{!}} B\n" + "{{!}}- \n" + "{{!}} C\n" + "{{!}} D\n" + "{{!}}}\n" + "}}}}", false));
	}

	public void testInvalidNoinclude() {
		assertEquals("test123 start\n" + "test123 end", wikiModel.parseTemplates("test123 start<noinclude>\n" + "test123 end"));
	}

	public void testInvalidIncludeonly() {
		assertEquals("test123 start", wikiModel.parseTemplates("test123 start<includeonly>\n" + "test123 end"));
	}

	public void testInvalidOnlyinclude() {
		assertEquals("test123 start\n" + "test123 end", wikiModel.parseTemplates("test123 start<onlyinclude>\n" + "test123 end"));
	}

	public void testMagicCURRENTYEAR() {
		// assertEquals("test 2010 test123",
		// wikiModel.parseTemplates("test {{CURRENTYEAR}} test123"));
	}

	public void testMagicPAGENAME01() {
		assertEquals("test [[PAGENAME]] test123", wikiModel.parseTemplates("test [[{{PAGENAME}}]] test123"));
	}

	public void testMagicPAGENAME02() {
		assertEquals("test [[Sandbox]] test123", wikiModel.parseTemplates("test [[{{PAGENAME:Sandbox}}]] test123"));
	}

	public void testMagicTALKPAGENAME01() {
		assertEquals("test [[Talk:Sandbox]] test123", wikiModel.parseTemplates("test [[{{TALKPAGENAME:Sandbox}}]] test123"));
	}

	public void testMagicTALKPAGENAME02() {
		assertEquals("test [[Help_talk:Sandbox]] test123", wikiModel.parseTemplates("test [[{{TALKPAGENAME:Help:Sandbox}}]] test123"));
	}

	public void testMagicTALKPAGENAME03() {
		assertEquals("test [[Help_talk:Sandbox]] test123", wikiModel.parseTemplates("test [[{{TALKPAGENAME:\nHelp:Sandbox}}]] test123"));
	}

	public void testTemplateSwitch() {
		// issue #32
		assertEquals(
				"1001",
				wikiModel
						.parseTemplates("{{#switch:y|y=1001|d={{#switch:w1001|w0=1|w-0=-8|{{#expr:\n"
								+ "1001*10{{#ifexpr:1001<0|-8|+1}}}}}}|c={{#expr:1001*100{{#ifexpr: 1001>0|-98|+1}}}}|m={{#expr:1001*1000{{#ifexpr:1001>0|-998|+1}}}}}}"));
	}
}