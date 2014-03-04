package cn.edu.ustc.analyzer.conf;

import java.util.Properties;

import cn.edu.ustc.common.FileTester;

public class AnalyseConfigure {
	private static Properties props=new FileTester("analyzer.properties").getProperties();
	
	public static String getWebName(String url)
	{
		return getProperty(getKey(url)+"@web");
	}

	public static String getTarget(String url)
	{
		return getProperty(getKey(url)+"@target");
	}
	
	public static String getJbnm(String url)
	{
		return getProperty(getKey(url)+"@jbnm");
	}
	
	public static String getCpnm(String url)
	{
		return getProperty(getKey(url)+"@cpnm");
	}
	
	public static String getKey(String url)
	{
		for(Object key:props.keySet())
			if(url.indexOf(key.toString())!=-1)
				return key.toString();
		return "";
	}
	
	public static String getProperty(String key)
	{
		return props.getProperty(key);
	}
}
