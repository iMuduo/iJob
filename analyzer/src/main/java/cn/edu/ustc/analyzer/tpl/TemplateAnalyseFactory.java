package cn.edu.ustc.analyzer.tpl;

import java.io.IOException;
import java.util.Hashtable;
import cn.edu.ustc.analyzer.conf.AnalyseConfigure;
import cn.edu.ustc.common.EasyDebug;
import cn.edu.ustc.common.FileSmart;
import cn.edu.ustc.common.StringHelper;

public final class TemplateAnalyseFactory {
	private static Hashtable<String, TemplateAnalyse> container=new Hashtable<String, TemplateAnalyse>();

	public static TemplateAnalyse geTemplateAnalyse(String url) throws IOException {
		String tpl=AnalyseConfigure.getKey(url);
		if(StringHelper.isNullOrEmpty(tpl))
		{
			EasyDebug.debug("Analyse Template:"+tpl+"is not exists!");
			return null;
		}
		if(!container.contains(tpl))
			container.put(tpl, new TemplateAnalyse(new FileSmart(AnalyseConfigure.getProperty(tpl)).getFile()));
		return container.get(tpl);
	}
}
