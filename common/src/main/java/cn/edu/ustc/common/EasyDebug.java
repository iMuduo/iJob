package cn.edu.ustc.common;

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
}
