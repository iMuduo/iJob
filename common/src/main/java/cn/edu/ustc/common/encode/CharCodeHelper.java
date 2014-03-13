package cn.edu.ustc.common.encode;

import java.net.URLEncoder;

public class CharCodeHelper {
	@SuppressWarnings("deprecation")
	public static String doubleEncoder(String w){
		return URLEncoder.encode(URLEncoder.encode(w));
	}
}
