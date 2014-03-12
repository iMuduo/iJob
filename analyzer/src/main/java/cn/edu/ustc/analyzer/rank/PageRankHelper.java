package cn.edu.ustc.analyzer.rank;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;

public class PageRankHelper {
	private final static String url="http://www.baidu.com/baidu";
	private final static Map<String,String> data=new Hashtable<String,String>();
	private final static Map<String, Long> cache=new Hashtable<String,Long>();
	static{
		data.put("tn", "baidu");
		data.put("word", "");
	}
	public static long getRank(String word){
		if(cache.containsKey(word))
			return cache.get(word);
		String rank=null;
		try {
			data.put("word", word);
			rank=Jsoup.connect(url).data(data).get().select(".nums").text();
			Pattern pattern=Pattern.compile("\\d");
			Matcher match=pattern.matcher(rank);
			StringBuffer sb=new StringBuffer();
			while(match.find())
				sb.append(match.group());
			rank=sb.toString();
			put(word, rank);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Long.valueOf(rank);
	}
	
	private static void put(String key,Long value){
		if(cache.size()<100000)
			cache.put(key, value);
	}
	
	private static void put(String key,String value){
		put(key, Long.valueOf(value));
	}
}
