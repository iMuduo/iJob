package cn.edu.ustc.spider.core;

import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.ustc.spider.conf.impl.JobInfo1;
import cn.edu.ustc.spider.conf.inf.ISpiderConfigure;
import cn.edu.ustc.spider.conf.inf.IWebSite;

public class Spider implements Runnable{
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
		Logger logger=Logger.getLogger(getClass());
		if(index<list.size())
		{
			IWebSite site=list.get(index++);
			String url=site.getNextUrl();
			List<JobInfo1> infoList=null;
			while((infoList=site.getJobInfo(url))!=null)
			{
				for(JobInfo1 info: infoList)
					logger.info(info.toString());
				url=site.getNextUrl();
			}
		}
	}
}
