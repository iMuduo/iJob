package cn.edu.ustc.common;

import org.apache.log4j.Logger;

public final class EasyDebug {
	private static boolean ctl=false;
	
	public static void setOn()
	{
		ctl=true;
	}
	public static void setOff()
	{
		ctl=false;
	}
	
	public static void debug(String info)
	{
		if(ctl)
			System.out.println(info);
	}
	
	public static void log4j(String msg,Class<?> target)
	{
		debug(msg);
		Logger.getLogger(target).info(msg);
	}
}
