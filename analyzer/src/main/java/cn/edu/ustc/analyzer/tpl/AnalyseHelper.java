package cn.edu.ustc.analyzer.tpl;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import cn.edu.ustc.analyzer.conf.AnalyseConfigure;
import cn.edu.ustc.analyzer.rank.PageRankHelper;
import cn.edu.ustc.common.EasyDebug;
import cn.edu.ustc.common.info.JobInfo1;

public final class AnalyseHelper {
	public static Map<String, ?> getResult(JobInfo1 info) {
		String url=info.getJob();
		Map<String, String> result=null;
		try {
			Document doc = Jsoup.connect(url).get();
			result = TemplateAnalyseFactory.geTemplateAnalyse(url).analyseContent(doc.select(
					AnalyseConfigure.getTarget(url)).text());
			
			if(result == null)
				return null;
			String cpnm=doc.select(AnalyseConfigure.getCpnm(url)).text();
			String jbnm=doc.select(AnalyseConfigure.getJbnm(url)).text();
			result.put("rank",String.valueOf(PageRankHelper.getRank(cpnm)+PageRankHelper.getRank(jbnm)));
			result.put("cpnm", cpnm);
			result.put("jbnm", jbnm);
			result.put("origin", AnalyseConfigure.getWebName(url));
			result.put("jburl", url);
			result.put("cpurl", info.getCompany());
		} catch (IOException e) {
			EasyDebug.log4j(url+"open error",get());
		}
		return result;
	}
	
	private static Class<?> get()
	{
		return new AnalyseHelper().getClass();
	}
}
