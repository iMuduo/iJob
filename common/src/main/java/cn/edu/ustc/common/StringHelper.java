package cn.edu.ustc.common;

public final class StringHelper {
	public static boolean isNullOrEmpty(Object obj)
	{
		if(obj==null) return true;
		if(obj.toString().isEmpty()) return true;
		return false;
	}
	
	public static boolean isNotNullOrEmpty(Object obj)
	{
		return !isNullOrEmpty(obj);
	}
	
	public static boolean isNotNull(Object obj)
	{
		return obj!=null;
	}
	
	public static String filter(Object obj){
		if(obj==null)
			return "";
		return obj.toString();
	}
}
