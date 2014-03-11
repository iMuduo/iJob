package cn.edu.ustc.site.service;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import cn.edu.ustc.common.DBHelper;
import cn.edu.ustc.common.MongoDB;
import cn.edu.ustc.common.info.WordRankInfo;
import cn.edu.ustc.site.base.PyHelper;

public class HotWordService extends BaseService{

	public void recordWord(String word){
		DBCollection db=MongoDB.getCollection("hotword");
		BasicDBObject conditon=new BasicDBObject();
		conditon.put("_id",  Pattern.compile(String.format("^%s$", word), Pattern.CASE_INSENSITIVE));
		DBObject obj=db.findOne(conditon);
		if(obj!=null){
			WordRankInfo wr=new WordRankInfo();
			wr.set_id(obj.get("_id").toString());
			wr.setTimes(Integer.valueOf(obj.get("times").toString())+1);
			wr.setPy(obj.get("py").toString());
			DBHelper.save(db, wr);
		}
		else{
			WordRankInfo wr=new WordRankInfo();
			wr.set_id(word);
			wr.setTimes(1);
			wr.setPy(PyHelper.getSimplePy(word));
			DBHelper.save(db, wr);
		}
	}
	
	public List<String> getHotWords(int n){
		DBCursor cursor=MongoDB.getCollection("hotword").find().sort(new BasicDBObject("times",-1)).limit(n);
		return getList(cursor);
	}
	
	public List<String> getAutoCompleteList(String w,int n){
		BasicDBObject conditon=new BasicDBObject();
		String query=String.format("function(){return /^%1$s.*$/gi.test(this.py) || /^%1$s.*$/gi.test(this._id)};",w);
		conditon.put("$where", query);
		DBCursor cursor=MongoDB.getCollection("hotword").find(conditon).sort(new BasicDBObject("times",-1)).limit(n);
		return getList(cursor);
	}
	
	private List<String> getList(DBCursor cursor){
		List<String> list=new LinkedList<String>();
		while(cursor.hasNext())
			list.add(cursor.next().get("_id").toString());
		return list;
	}
}
