package cn.edu.ustc.common.encode;

import java.io.UnsupportedEncodingException;

public class EscapeUnescape {
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		if((src.length() % 4)!=0)
			return "";
		StringBuffer re=new StringBuffer();
		for(int i=0;i<src.length();i=i+4)
			try {
				re.append(new String(Integer.valueOf(src.substring(i,i+4), 16).toString().getBytes(),"utf-8"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return re.toString();
	}
}