package cn.edu.ustc.spider.conf.inf;

import java.util.List;

import cn.edu.ustc.spider.conf.impl.JobInfo1;


public interface IWebSite {
	public void setName(String name);
	public void setResultClassName(String className);
	public void setJobAt(String at);
	public void setCompanyAt(String at);
	public void setJobLike(String like);
	public void setCompanyLike(String like);
	public void setSearchUrl(String url);
	public void setPageParams(String page);
	public void setPriority(int priority);
	public String getSearchUrl();
	public String getNextUrl();
	public String getName();
	public int getPriority();
	public List<JobInfo1> getJobInfo(String url);
	public boolean isJobInfo(String job);
	public boolean isCompanyInfo(String company);
}
