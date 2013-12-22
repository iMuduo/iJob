package cn.edu.ustc.analyzer;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.edu.uset.analyzer.tpl.JobInfo2;
import cn.edu.uset.analyzer.tpl.TemplateAnalyse;
import cn.edu.ustc.common.DBHelper;
import cn.edu.ustc.common.MongoDB;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * Hello world!
 *
 */
public class Program 
{
    public static void main( String[] args ) throws IOException
    {
        DBCollection db=MongoDB.getCollection("jobInfo1");
        TemplateAnalyse ta=new TemplateAnalyse(new Program().getClass().getClassLoader().getResourceAsStream("chinahr.tpl"));
        while(true)
        {
        	DBObject obj=db.findOne();
        	if(obj.get("job").toString().indexOf("chinahr.com")!=-1)
        	{
            	Document doc=Jsoup.connect(obj.get("job").toString()).get();
            	Map<String, String> result=ta.analyse(doc.select(".nowjobbox-left").text());
            	JobInfo2 info=new JobInfo2();
            	info.setMap(result);
            	DBHelper.save(MongoDB.getCollection("jobInfo2"), info);	
        	}
        	db.remove(obj);
        }
    }
}
