package cn.edu.ustc.spider.conf.inf;

import java.util.List;
import cn.edu.ustc.spider.conf.impl.JobInfo1;

public interface IWebSite extends Comparable<IWebSite>{
	public void setName(String name);
	public void setJobAt(String select);
	public void setCompanyAt(String select);
	public void setSearchUrl(String url);
	public void setPageParams(String page);
	public void setPriority(int priority);
	public void setMaxPages(int max);
	public String getSearchUrl();
	public String getNextUrl();
	public String getName();
	public int getPriority();
	public boolean isMaxPages();
	public List<JobInfo1> getJobInfo(String url);
}
