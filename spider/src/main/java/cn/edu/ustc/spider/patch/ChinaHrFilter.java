package cn.edu.ustc.spider.patch;

public class ChinaHrFilter {
	public static String filter(String keyword){
		keyword=keyword.replaceAll("#", "%2523");
		keyword=keyword.replaceAll("\\/", "%252f");
		keyword=keyword.replaceAll("@", "%40");
		keyword=keyword.replaceAll("\\+", "%2B");
		return keyword;
	}
}
