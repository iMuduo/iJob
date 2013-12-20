package cn.edu.ustc.spider.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import cn.edu.ustc.common.MongoDB;
import cn.edu.ustc.spider.conf.impl.JobInfo1;
import cn.edu.ustc.spider.conf.inf.ISpiderConfigure;
import cn.edu.ustc.spider.conf.inf.IWebSite;

public class Spider implements Runnable{
	/**
	 * 
	 */
	private static int index=0;
	private static  ISpiderConfigure conf=null;
	private static  List<IWebSite> list=null;
	
	public static void setConfigure(ISpiderConfigure confgiure)
	{
		conf=confgiure;
		list=conf.getWebSites();
	}
	
	public void run()
	{
		DBCollection db=MongoDB.getCollection("jobInfo1");
		if(index<list.size())
		{
			IWebSite site=list.get(index++);
			String url=site.getNextUrl();
			List<JobInfo1> infoList=null;
			while((infoList=site.getJobInfo(url))!=null)
			{
				for(JobInfo1 info: infoList)
				{
					DBObject object=new BasicDBObject();
					object.put("_id", info.getJob());
					object.put("job", info.getJob());
					object.put("company", info.getCompany());
					db.insert(object);
				}
				url=site.getNextUrl();
			}
		}
	}
	
	public static void save() throws IOException
	{
		File file=new File("spider.obj");
		if(file.exists())
			file.delete();
		FileOutputStream fos=new FileOutputStream(file);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(list);
		oos.close();
		fos.close();
	}
	
	public static boolean hasStatus()
	{
		File file=new File("spider.obj");
		return file.exists();
	}
	
	@SuppressWarnings("unchecked")
	public static void getStatus() throws IOException, ClassNotFoundException
	{
		File file=new File("spider.obj");
		FileInputStream fis=new FileInputStream(file);
		ObjectInputStream ois =new ObjectInputStream(fis);
		list=(List<IWebSite>)ois.readObject();
	}
}
