package cn.edu.ustc.spider.conf.impl;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import cn.edu.ustc.common.EasyDebug;
import cn.edu.ustc.common.info.JobInfo1;
import cn.edu.ustc.common.net.UrlFetchHelper;
import cn.edu.ustc.spider.conf.inf.IWebSite;

public class WebSite implements IWebSite, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2161982897681866944L;
	private String name;
	private String jobAt;
	private String companyAt;
	private int priority;
	private String url;
	private String page;
	private int currPage;
	private int max;

	public WebSite() {
		this.currPage = 0;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setJobAt(String select) {
		this.jobAt = select;
	}

	@Override
	public void setCompanyAt(String select) {
		this.companyAt = select;
	}

	@Override
	public void setSearchUrl(String url) {
		this.url = url;
	}

	@Override
	public void setPageParams(String page) {
		this.page = page;
	}

	@Override
	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public String getSearchUrl() {
		return this.url;
	}

	@Override
	public String getNextUrl() {
		return String.format("%1$s%2$s%3$d", this.url, this.page,
				++this.currPage);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getPriority() {
		return this.priority;
	}

	@Override
	public List<JobInfo1> getJobInfo(String url) {
		List<JobInfo1> jobList = new LinkedList<JobInfo1>();
		Document doc=UrlFetchHelper.getDocument(url);
		Elements jobs = doc.select(jobAt);
		Elements companys = doc.select(companyAt);
		if (jobs.size() != companys.size())
			EasyDebug
					.log4j((String
							.format("fetch %1$s error,becasue job count:%2$d not equal company count:%3$d!",
									url, jobs.size(), companys.size())),
							getClass());
		else
			for (int i = 0; i < jobs.size(); i++) {
				JobInfo1 info = new JobInfo1();
				info.setJob(jobs.get(i).attr("href"));
				info.setCompany(companys.get(i).attr("href"));
				jobList.add(info);
			}
		return jobList;
	}

	@Override
	public int compareTo(IWebSite o) {
		// TODO Auto-generated method stub
		return new Integer(o.getPriority()).compareTo(new Integer(o
				.getPriority()));
	}

	@Override
	public void setMaxPages(int max) {
		// TODO Auto-generated method stub
		this.max = max;
	}

	@Override
	public boolean isMaxPages() {
		// TODO Auto-generated method stub
		return max == currPage;
	}
}
