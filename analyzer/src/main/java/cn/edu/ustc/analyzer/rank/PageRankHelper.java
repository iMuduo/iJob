package cn.edu.ustc.analyzer.rank;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;

import cn.edu.ustc.common.StringHelper;

public class PageRankHelper {
	private final static String url="http://www.baidu.com/baidu";
	private final static String defaultRank="10000";
	private final static Map<String,String> data=new Hashtable<String,String>();
	private final static Map<String, Long> cache=new Hashtable<String,Long>();
	static{
		data.put("tn", "baidu");
		data.put("word", "");
	}
	public static long getRank(String word){
		put(word, "");
		if(cache.containsKey(word))
			return cache.get(word);
		String target=null;
		try {
			data.put("word", word);
			target=Jsoup.connect(url).data(data).get().select(".nums").text();
			Pattern pattern=Pattern.compile("\\d");
			Matcher match=pattern.matcher(target);
			StringBuffer sb=new StringBuffer();
			while(match.find())
				sb.append(match.group());
			put(word, sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cache.get(word);
	}
	
	private static void put(String key,Long value){
		if(cache.size()<100000)
			cache.put(key, value);
	}
	
	private static void put(String key,String value){
		if(StringHelper.isNullOrEmpty(value))
			value=defaultRank;
		put(key, Long.valueOf(value));
	}
}
