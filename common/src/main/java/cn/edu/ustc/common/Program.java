package cn.edu.ustc.common;

import java.util.Hashtable;
import java.util.Map;

public class Program {
	public static void main(String[] args){
		TestInfo info=new TestInfo();
		Map<String,Object> map=new Hashtable<String,Object>();
		map.put("name", "muduo");
		map.put("address", "xinye");
		info.setMap(map);
		map=info.getMap();
		for(String field:map.keySet())
			System.out.println(field+":"+map.get(field));
		info.setField("name", "yuyansong");
		System.out.println(info.getField("name"));
	}
}
