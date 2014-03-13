package cn.edu.ustc.common.net;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public final class UrlFetchHelper {
	public static Document getDocument(String url){
		String html=new UrlFetcher().fetch(url);
		return Jsoup.parse(html);
	}
}
