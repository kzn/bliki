package info.bliki.wiki.namespaces;

import java.util.ResourceBundle;

/**
 * Mediawiki namespace for a specific language. See <a
 * href="http://www.mediawiki.org/wiki/Manual:Namespace#Built-in_namespaces"
 * >Mediawiki - Manual:Namespace</a>
 * 
 */
public interface INamespace {
	/**
	 * Alias for direct links to media files.
	 */
	public final static Integer MEDIA = Integer.valueOf(-2);

	/**
	 * Holds special pages.
	 */
	public final static Integer SPECIAL = Integer.valueOf(-1);

	/**
	 * "Real" content; articles. Has no prefix.
	 */
	public final static Integer MAIN = Integer.valueOf(0);

	/**
	 * Talk pages of "Real" content
	 */
	public final static Integer TALK = Integer.valueOf(1);

	/**
   * 
   */
	public final static Integer USER = Integer.valueOf(2);

	/**
	 * Talk pages for User Pages
	 */
	public final static Integer USER_TALK = Integer.valueOf(3);

	/**
	 * Information about the wiki. Prefix is the same as $wgSitename of the PHP
	 * installation.
	 */
	public final static Integer PROJECT = Integer.valueOf(4);

	/**
   * 
   */
	public final static Integer PROJECT_TALK = Integer.valueOf(5);

	/**
	 * Media description pages.
	 */
	public final static Integer FILE = Integer.valueOf(6);

	/**
   * 
   */
	public final static Integer FILE_TALK = Integer.valueOf(7);

	/**
	 * Site interface customisation. Protected.
	 */
	public final static Integer MEDIAWIKI = Integer.valueOf(8);

	/**
   * 
   */
	public final static Integer MEDIAWIKI_TALK = Integer.valueOf(9);

	/**
	 * Template pages.
	 */
	public final static Integer TEMPLATE = Integer.valueOf(10);

	/**
   * 
   */
	public final static Integer TEMPLATE_TALK = Integer.valueOf(11);

	/**
	 * Help pages.
	 */
	public final static Integer HELP = Integer.valueOf(12);

	/**
   * 
   */
	public final static Integer HELP_TALK = Integer.valueOf(13);

	/**
	 * Category description pages.
	 */
	public final static Integer CATEGORY = Integer.valueOf(14);

	/**
   * 
   */
	public final static Integer CATEGORY_TALK = Integer.valueOf(15);

	public String getCategory();

	public String getCategory_talk();

	public String getCategory_talk2();

	public String getCategory2();

	public String getHelp();

	public String getHelp_talk();

	public String getHelp_talk2();

	public String getHelp2();

	public String getImage();

	public String getImage_talk();

	public String getImage_talk2();

	public String getImage2();

	/**
	 * Get the &quot;Media&quot; namespace for the current language.
	 * 
	 * @return the namespace
	 */
	public String getMedia();

	public String getMedia2();

	public String getMediaWiki();

	public String getMediaWiki_talk();

	public String getMediaWiki_talk2();

	public String getMediaWiki2();

	public String getMeta();

	public String getMeta_talk();

	public String getMeta_talk2();

	public String getMeta2();

	public String getNamespaceByLowercase(String lowercaseNamespace);

	public String getNamespaceByNumber(int numberCode);

	public ResourceBundle getResourceBundle();

	public String getSpecial();

	public String getSpecial2();

	public String getTalk();

	public String getTalk2();

	public String getTemplate();

	public String getTemplate_talk();

	public String getTemplate_talk2();

	public String getTemplate2();

	public String getUser();

	public String getUser_talk();

	public String getUser_talk2();

	public String getUser2();

	/**
	 * Get the Talk namespace.
	 * 
	 * @param namespace
	 * @return
	 */
	public String getTalkspace(String namespace);
	
	public boolean isMedia(String name);
	public boolean isSpecial(String name);
	public boolean isTalk(String name);
	public boolean isUser(String name);
	public boolean isUserTalk(String name);
	public boolean isProject(String name);
	public boolean isProjectTalk(String name);
	public boolean isFile(String name);
	public boolean isFileTalk(String name);
	public boolean isMediaWiki(String name);
	public boolean isMediaWikiTalk(String name);	
	public boolean isTemplate(String name);
	public boolean isTemplateTalk(String name);
	public boolean isCategory(String name);
	public boolean isCategoryTalk(String name);

	
	
}