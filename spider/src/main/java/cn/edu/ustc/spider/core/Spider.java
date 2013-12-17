package cn.edu.ustc.spider.core;

import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.ustc.spider.conf.impl.JobInfo1;
import cn.edu.ustc.spider.conf.inf.ISpiderConfigure;
import cn.edu.ustc.spider.conf.inf.IWebSite;

public class Spider {
	private ISpiderConfigure conf=null;

	public Spider(ISpiderConfigure conf)
	{
		this.conf=conf;
	}
	
	public void setConfigure(ISpiderConfigure conf) {
		this.conf=conf;
	}
	
	public void get()
	{
		Logger logger=Logger.getLogger(getClass());
		List<IWebSite> list=conf.getWebSites();
		for(IWebSite site:list)
		{
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
