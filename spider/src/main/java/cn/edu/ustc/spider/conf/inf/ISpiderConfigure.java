package cn.edu.ustc.spider.conf.inf;

import java.util.List;

public interface ISpiderConfigure {
	public void setConfigureFile(String confPath,IWebSiteFactory factory);
	public List<IWebSite> getWebSites();
	public void refresh();
	public int getSiteCount();
}
