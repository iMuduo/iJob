package cn.edu.ustc.spider.conf.impl;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.edu.ustc.spider.conf.inf.IWebSite;

public class WebSite implements IWebSite{
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
	
	public WebSite()
	{
		this.currPage=0;
	}
	
	@Override
	public void setName(String name) {
		this.name=name;
	}

	@Override
	public void setResultClassName(String className) {
		this.className=className;
	}

	@Override
	public void setJobAt(String at) {
		this.jobAt=at;
	}

	@Override
	public void setCompanyAt(String at) {
		this.companyAt=at;
	}
	
	@Override
	public void setJobLike(String like) {
		this.jobAt=like;
	}

	@Override
	public void setCompanyLike(String like) {
		this.companyAt=like;
	}

	@Override
	public void setSearchUrl(String url) {
		this.url=url;
	}

	@Override
	public void setPageParams(String page) {
		this.page=page;
	}

	@Override
	public void setPriority(int priority) {
		this.priority=priority;
	}

	@Override
	public String getSearchUrl() {
		return this.url;
	}

	@Override
	public String getNextUrl() {
		return String.format("%1$s&%2$s=%3$d", this.url,this.page,++this.currPage);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getPriority() {
		return this.priority;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobInfo1> getJobInfo(String url) {
		System.err.println(url);
		Logger logger=Logger.getLogger(getClass());
		Document doc;
		List<JobInfo1> jobInfo1List=new LinkedList<JobInfo1>();
		try {
			SAXReader reader=new SAXReader();
			URLConnection con=new URL(url).openConnection();
			DataInput di=new DataInputStream(con.getInputStream());
			String line;
			while((line=di.readLine())!=null)
			{
				System.out.println(line);
			}
			doc=reader.read(new URL(url));
			List<Element> jobList=doc.selectNodes(String.format("//a[@class=%1$s]", this.jobAt));
			List<Element> companyList=doc.selectNodes(String.format("//a[@class=%1$s]", this.companyAt));
			
			if(jobList.size()!=companyList.size())
				logger.error(String.format("%1$s mathc error!", url));
			
			for(int i=0;i<jobList.size();i++)
			{
				JobInfo1 info=new JobInfo1();
				info.setCompany(companyList.get(i).attributeValue("href"));
				info.setJob(jobList.get(i).attributeValue("href"));
				jobInfo1List.add(info);
			}
			
		} catch (MalformedURLException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (DocumentException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(jobInfo1List.size()==0)
			logger.info(String.format("%1$s end fetch at %2$s", this.getName(),url));
		return jobInfo1List;
	}

	@Override
	public boolean isJobInfo(String job) {
		return job.indexOf(this.jobLike)!=-1;
	}

	@Override
	public boolean isCompanyInfo(String company) {
		return company.indexOf(this.companyLike)!=-1;
	}

}
