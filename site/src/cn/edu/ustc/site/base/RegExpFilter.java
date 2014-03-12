package cn.edu.ustc.site.base;

public final class RegExpFilter {
	public static String filter(String str){
		return str.replaceAll("([\\.\\$\\^\\{\\}\\[\\]\\(\\)\\|\\*\\+\\?\\\\])", "\\\\$1");
	}
}
