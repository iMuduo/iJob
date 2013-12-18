package cn.edu.ustc.spider.conf.impl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.edu.ustc.spider.conf.inf.IWebSite;

public class WebSite implements IWebSite {
	private String name;
	private String className;
	private String jobAt;
	private String companyAt;
	private String jobLike;
	private String companyLike;
	private int priority;
	private String url;
	private String page;
	private int currPage;

	public WebSite() {
		this.currPage = 0;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setResultClassName(String className) {
		this.className = className;
	}

	@Override
	public void setJobAt(String at) {
		this.jobAt = at;
	}

	@Override
	public void setCompanyAt(String at) {
		this.companyAt = at;
	}

	@Override
	public void setJobLike(String like) {
		this.jobLike = like;
	}

	@Override
	public void setCompanyLike(String like) {
		this.companyLike = like;
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
		return String.format("%1$s&%2$s=%3$d", this.url, this.page,
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
		System.err.println(url);
		Logger logger = Logger.getLogger(getClass());
		Document doc;
		List<JobInfo1> jobList = new LinkedList<JobInfo1>();
		try {
			doc = Jsoup.connect(url).get();
			Elements jobs = doc.select(String.format(".%2$s", className,
					jobAt));
			Elements companys = doc.select(String.format(".%2$s",
					className, companyAt));
			if (jobs.size() != companys.size())
				logger.info(String
						.format("fetch %1$s error,becasue job count not equal company count!",
								url));
			else
				for (int i = 0; i < jobs.size(); i++) {
					JobInfo1 info = new JobInfo1();
					info.setJob(jobs.get(i).attr("href"));
					info.setCompany(companys.get(i).attr("href"));
					jobList.add(info);
				}
		} catch (IOException e) {
			logger.info(String.format("fetch %1$s error!", url));
			e.printStackTrace();
		}
		return jobList;
	}

	@Override
	public boolean isJobInfo(String job) {
		return job.indexOf(this.jobLike) != -1;
	}

	@Override
	public boolean isCompanyInfo(String company) {
		return company.indexOf(this.companyLike) != -1;
	}

}
