package cn.edu.ustc.site.enhance;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import cn.edu.ustc.common.MongoDB;
import cn.edu.ustc.common.StringHelper;
import cn.edu.ustc.site.base.RegExpFilter;
import cn.edu.ustc.site.base.S;
import cn.edu.ustc.site.service.HotWordService;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class SearchEnhance {
	private final String[] fields=new String[]{"cpnm","jbnm","cpscale","cptype","cptrade","genderrq","rcnos","agerq","wkform","deadline","degreerq","salary","wkxp","wkplace","jbdesc","wkrq","benefit","date","cpinfo","origin","jburl","cpurl"};
	private final int per=20;
	private Map<String, String> map;
	public SearchEnhance(Map<String, String> map){
		this.map=map;
	}
	public String getJson(int i){
		DBCursor query=getPage(i);
		 List<Hashtable<String, String>> result=new LinkedList<Hashtable<String,String>>();
		while(query.hasNext())
		{
			Hashtable<String, String> row=new Hashtable<String, String>();
			DBObject obj=query.next();
			for(int j=0;j<fields.length;j++)
				row.put(fields[j], (String) (StringHelper.isNullOrEmpty(obj.get(fields[j]))?"²»Ïê":obj.get(fields[j])));
			result.add(row);
		}
		S.get(HotWordService.class).recordWord(map.get("keyword"));
		return new Gson().toJson(result);
	}
	
	public int getCount(){
		return getQuery().count();
	}
	
	private DBCursor getPage(int i){
		return getQuery().skip(i*per).limit(per);
	}
	
	private DBCursor getQuery(){
		DBCollection db=MongoDB.getCollection("jobInfo2");
		BasicDBObject conditon=new BasicDBObject();
		for(String k:map.keySet()){
			String v=map.get(k);
			if(StringHelper.isNotNullOrEmpty(v)){
				if(k.equals("keyword"))
					k="jbnm";
				conditon.put(k,  Pattern.compile(String.format("^.*%s.*$", RegExpFilter.filter(v)), Pattern.CASE_INSENSITIVE));
			}
		}
		return db.find(conditon).sort(new BasicDBObject("rank",-1));
	}
}
