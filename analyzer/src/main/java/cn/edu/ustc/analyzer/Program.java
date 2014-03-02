package cn.edu.ustc.analyzer;

import java.io.IOException;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import cn.edu.ustc.analyzer.tpl.JobInfo2;
import cn.edu.ustc.analyzer.tpl.TemplateAnalyseFactory;
import cn.edu.ustc.common.DBHelper;
import cn.edu.ustc.common.MongoDB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * Hello world!
 * 
 */
public class Program {
	public static void main(String[] args) throws IOException {
		DBCollection db = MongoDB.getCollection("jobInfo1");
		while (true) {
			DBObject obj = db.findOne();
			if(obj == null)
				continue;
			if (obj.get("job").toString().indexOf("http://www.chinahr.com/") != -1) {
				try {
					Document doc = Jsoup.connect(obj.get("job").toString())
							.get();
					Map<String, String> result = TemplateAnalyseFactory.geTemplateAnalyse("chinahr").analyse(doc.select(
							".nowjobbox-left").text());
					result.put("cpnm", doc.select(".nowjobbox-name_c>a").text());
					result.put("jbnm", doc.select(".nowjobbox-name>a").text());
					JobInfo2 info = new JobInfo2();
					info.setMap(result);
					DBHelper.save(MongoDB.getCollection("jobInfo2"), info);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.toString());
				}
			} else if (obj.get("job").toString().indexOf("http://jobs.zhaopin.com/") != -1) {
				try {
					Document doc = Jsoup.connect(obj.get("job").toString())
							.get();
					Map<String, String> result = TemplateAnalyseFactory.geTemplateAnalyse("zhaopin").analyse(doc.select(
							".terminalpage-left").text());
					result.put("cpnm", doc.select(".terminalpage-table h2").text());
					result.put("jbnm", doc.select(".terminalpage-table h1").text());
					JobInfo2 info = new JobInfo2();
					info.setMap(result);
					DBHelper.save(MongoDB.getCollection("jobInfo2"), info);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.toString());
				}
			} else if (obj.get("job").toString().indexOf("http://search.51job.com/") != -1) {
				try {
					Document doc = Jsoup.connect(obj.get("job").toString())
							.get();
					Map<String, String> result = TemplateAnalyseFactory.geTemplateAnalyse("51job").analyse(doc.select(
							".s_txt_jobs table").text());
					result.put("cpnm", doc.select(".jobs_1 a:first").text());
					result.put("jbnm", doc.select(".sr_bt").text());
					JobInfo2 info = new JobInfo2();
					info.setMap(result);
					DBHelper.save(MongoDB.getCollection("jobInfo2"), info);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
 			db.remove(obj);
		}
	}
}
