package cn.edu.ustc.analyzer;

import java.io.IOException;
import java.util.Map;

import cn.edu.ustc.analyzer.tpl.AnalyseHelper;
import cn.edu.ustc.common.DBHelper;
import cn.edu.ustc.common.MongoDB;
import cn.edu.ustc.common.info.JobInfo1;
import cn.edu.ustc.common.info.JobInfo2;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class Program {
	public static void main(String[] args) throws IOException {
		DBCollection db = MongoDB.getCollection("jobInfo1");
		while (true) {
			DBObject obj = db.findOne();

			if (obj == null)
				continue;

			JobInfo1 info1 = new JobInfo1();
			info1.setJob(obj.get("job").toString());
			info1.setCompany(obj.get("company").toString());

			Map<String, ?> result = AnalyseHelper.getResult(info1);

			if (result != null) {
				JobInfo2 info2 = new JobInfo2();
				info2.setMap(result);
				DBHelper.save(MongoDB.getCollection("jobInfo2"), info2);
			}
			db.remove(obj);
		}
	}
}
