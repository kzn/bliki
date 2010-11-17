package info.bliki.wiki.namespaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class XMLNamespace implements INamespace {
	String[] defNS; // default namespaces
	String[] localeNS; // custom namespaces
	Map<Integer, String> customNS;

	
	public XMLNamespace() {
		defNS = new String[20];
		localeNS = new String[20];
		customNS = new HashMap<Integer, String>(16);
		addDefault(-2, "Media");
		addDefault(-1, "Special");
		addDefault(0, "");
		addDefault(1, "Talk");
		addDefault(2, "User");
		addDefault(3, "User Talk");
		addDefault(4, "Project");
		addDefault(5, "Project Talk");
		addDefault(6, "File");
		addDefault(7, "File Talk");
		addDefault(8, "MediaWiki");
		addDefault(9, "MediaWiki Talk");
		addDefault(10, "Template");
		addDefault(11, "Template Talk");
		addDefault(12, "Help");
		addDefault(13, "Help Talk");
		addDefault(14, "Category");
		addDefault(15, "Category Talk");
	}
	
	/**
	 * Get array offset for base namespaces
	 * @param id
	 * @return
	 */
	public int getBaseOffset(int id) {
		return id + 2;
	}
	
	private void addDefault(int id, String name) {
		defNS[getBaseOffset(id)] = name;
	}
	
	public void addNamespace(int id, String name) {
		if(id < 100)
			localeNS[getBaseOffset(id)] = name;
		else
			customNS.put(id, name);
	}
	
	public boolean isCustomNamespace(int id) {
		return id > 100;
	}
	
	private boolean checkNamespace(String name, int key) {
		int off = getBaseOffset(key);
		return defNS[off].equals(name) || localeNS[off].equals(name);
	}
	

	
	@Override
	public String getCategory() {
		return defNS[getBaseOffset(INamespace.CATEGORY)];
	}

	@Override
	public String getCategory_talk() {
		return defNS[getBaseOffset(INamespace.CATEGORY_TALK)];
	}

	@Override
	public String getCategory_talk2() {
		return localeNS[getBaseOffset(INamespace.CATEGORY_TALK)];
	}

	@Override
	public String getCategory2() {
		return localeNS[getBaseOffset(INamespace.CATEGORY)];
	}

	@Override
	public String getHelp() {
		return defNS[getBaseOffset(INamespace.HELP)];
	}

	@Override
	public String getHelp_talk() {
		return defNS[getBaseOffset(INamespace.HELP_TALK)];
	}

	@Override
	public String getHelp_talk2() {
		return localeNS[getBaseOffset(INamespace.HELP_TALK)];
	}

	@Override
	public String getHelp2() {
		return localeNS[getBaseOffset(INamespace.HELP)];
	}

	@Override
	public String getImage() {
		return defNS[getBaseOffset(INamespace.FILE)];
	}

	@Override
	public String getImage_talk() {
		return defNS[getBaseOffset(INamespace.FILE_TALK)];
	}

	@Override
	public String getImage_talk2() {
		return localeNS[getBaseOffset(INamespace.FILE_TALK)];
	}

	@Override
	public String getImage2() {
		return localeNS[getBaseOffset(INamespace.FILE)];
	}

	@Override
	public String getMedia() {
		return defNS[getBaseOffset(INamespace.MEDIA)];
	}

	@Override
	public String getMedia2() {
		return null;
	}

	@Override
	public String getMediaWiki() {
		return defNS[getBaseOffset(INamespace.MEDIAWIKI)];
	}

	@Override
	public String getMediaWiki_talk() {
		return defNS[getBaseOffset(INamespace.MEDIAWIKI_TALK)];
	}

	@Override
	public String getMediaWiki_talk2() {
		return localeNS[getBaseOffset(INamespace.TALK)];
	}

	@Override
	public String getMediaWiki2() {
		return localeNS[getBaseOffset(INamespace.MEDIAWIKI)];
	}

	@Override
	public String getMeta() {
		return defNS[getBaseOffset(INamespace.PROJECT)];
	}

	@Override
	public String getMeta_talk() {
		return defNS[getBaseOffset(INamespace.PROJECT_TALK)];
	}

	@Override
	public String getMeta_talk2() {
		return localeNS[getBaseOffset(INamespace.PROJECT_TALK)];
	}

	@Override
	public String getMeta2() {
		return localeNS[getBaseOffset(INamespace.PROJECT)];
	}

	@Override
	public String getNamespaceByLowercase(String lowercaseNamespace) {
		return null;
	}

	@Override
	public String getNamespaceByNumber(int numberCode) {
		return null;
	}

	@Override
	public ResourceBundle getResourceBundle() {
		return null;
	}

	@Override
	public String getSpecial() {
		return defNS[getBaseOffset(INamespace.SPECIAL)];
	}

	@Override
	public String getSpecial2() {
		return localeNS[getBaseOffset(INamespace.SPECIAL)];
	}

	@Override
	public String getTalk() {
		return defNS[getBaseOffset(INamespace.TALK)];
	}

	@Override
	public String getTalk2() {
		return null;
	}

	@Override
	public String getTemplate() {
		return defNS[getBaseOffset(INamespace.TEMPLATE)];
	}

	@Override
	public String getTemplate_talk() {
		return defNS[getBaseOffset(INamespace.TEMPLATE_TALK)];
	}

	@Override
	public String getTemplate_talk2() {
		return localeNS[getBaseOffset(INamespace.TEMPLATE_TALK)];
	}

	@Override
	public String getTemplate2() {
		return localeNS[getBaseOffset(INamespace.TEMPLATE)];
	}

	@Override
	public String getUser() {
		return defNS[getBaseOffset(INamespace.USER)];
	}

	@Override
	public String getUser_talk() {
		return defNS[getBaseOffset(INamespace.USER_TALK)];
	}

	@Override
	public String getUser_talk2() {
		return localeNS[getBaseOffset(INamespace.USER_TALK)];
	}

	@Override
	public String getUser2() {
		return localeNS[getBaseOffset(INamespace.USER)];
	}

	@Override
	public String getTalkspace(String namespace) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMedia(String name) {
		return checkNamespace(name, INamespace.MEDIA);
	}

	@Override
	public boolean isSpecial(String name) {
		return checkNamespace(name, INamespace.SPECIAL);
	}

	@Override
	public boolean isTalk(String name) {
		return checkNamespace(name, INamespace.TALK);
	}

	@Override
	public boolean isUser(String name) {
		return checkNamespace(name, INamespace.USER);
	}

	@Override
	public boolean isUserTalk(String name) {
		return checkNamespace(name, INamespace.USER_TALK);
	}

	@Override
	public boolean isProject(String name) {
		return checkNamespace(name, INamespace.PROJECT);
	}

	@Override
	public boolean isProjectTalk(String name) {
		return checkNamespace(name, INamespace.PROJECT_TALK);
	}

	@Override
	public boolean isFile(String name) {
		return checkNamespace(name, INamespace.FILE);
	}

	@Override
	public boolean isFileTalk(String name) {
		return checkNamespace(name, INamespace.FILE_TALK);
	}

	@Override
	public boolean isMediaWiki(String name) {
		return checkNamespace(name, INamespace.MEDIAWIKI);
	}

	@Override
	public boolean isMediaWikiTalk(String name) {
		return checkNamespace(name, INamespace.MEDIAWIKI_TALK);
	}

	@Override
	public boolean isTemplate(String name) {
		return checkNamespace(name, INamespace.TEMPLATE);
	}

	@Override
	public boolean isTemplateTalk(String name) {
		return checkNamespace(name, INamespace.TEMPLATE_TALK);
	}

	@Override
	public boolean isCategory(String name) {
		return checkNamespace(name, INamespace.CATEGORY);
	}

	@Override
	public boolean isCategoryTalk(String name) {
		return checkNamespace(name, INamespace.CATEGORY_TALK);
	}

}
