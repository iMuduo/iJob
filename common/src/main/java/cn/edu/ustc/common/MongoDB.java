package cn.edu.ustc.common;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public final class MongoDB {
	private MongoDB(){}
	private static final String host = "127.0.0.1";
	private static final int port = 27017;
	private static final String dbase = "ijob";
	private static Mongo mg = null;
	private static DB db=null;
	static {
		try {
			mg=new Mongo(host,port);
			db=mg.getDB(dbase);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
	public static DBCollection getCollection(String collection)
	{
		return db.getCollection(collection);
	}
	
	public static void close()
	{
		if(mg!=null)
			mg.close();
	}
}
