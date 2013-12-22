package cn.edu.ustc.common;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public final class DBHelper {
	private DBHelper(){}
	public static void save(DBCollection collection,ActiveRecord record)
	{
		DBObject obj=new BasicDBObject(record.getMap());
		collection.save(obj);
	}
}
