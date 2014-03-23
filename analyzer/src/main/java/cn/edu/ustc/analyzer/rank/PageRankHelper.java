package cn.edu.ustc.analyzer.rank;

import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;

import cn.edu.ustc.common.StringHelper;
import cn.edu.ustc.common.net.UrlFetchHelper;

public class PageRankHelper {
	private final static String scheme="http";
	private final static String host="www.baidu.com";
	private final static String path="/baidu";
	private final static String defaultRank="10000";
	private final static Map<String,String> data=new Hashtable<String,String>();
	private final static Map<String, Long> cache=new Hashtable<String,Long>();
	
	static{
		data.put("tn", "baidu");
		data.put("word", "");
	}
	public static long getRank(String word){
		if(cache.containsKey(word))
			return cache.get(word);
		put(word, "");
		String target=null;
		data.put("word", word);
		Document doc=UrlFetchHelper.getDocumentfetch(scheme, host, path, data);
		target=doc.select(".nums").text();
		Pattern pattern=Pattern.compile("\\d");
		Matcher match=pattern.matcher(target);
		StringBuffer sb=new StringBuffer();
		while(match.find())
			sb.append(match.group());
		put(word, sb.toString());
		return cache.get(word);
	}
	
	private static void put(String key,Long value){
		if(cache.size()>100000)
			cache.clear();
		cache.put(key, value);
	}
	
	private static void put(String key,String value){
		if(StringHelper.isNullOrEmpty(value))
			value=defaultRank;
		put(key, Long.valueOf(value));
	}
}
