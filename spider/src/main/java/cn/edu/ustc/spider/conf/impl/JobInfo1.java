package cn.edu.ustc.spider.conf.impl;

public class JobInfo1 {
	private String job;
	private String company;
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	public String toString()
	{
		return String.format("job:%1$s   company:%2$s", this.job,this.company);
	}
}
