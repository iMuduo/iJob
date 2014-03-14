package cn.edu.ustc.common.net;

import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public final class UrlFetchHelper {
	public static Document getDocument(String url){
		String html=new UrlFetcher().fetch(url);
		return Jsoup.parse(html);
	}
	
	public static Document getDocumentfetch(String scheme,String host,String path,Map<String, String> data){
		String html=new UrlFetcher().fetch(scheme,host,path,data);
		return Jsoup.parse(html);
	}
}
