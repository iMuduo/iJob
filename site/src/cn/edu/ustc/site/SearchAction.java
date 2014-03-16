package cn.edu.ustc.site;

import java.io.UnsupportedEncodingException;

import cn.edu.ustc.site.base.BaseAction;
import cn.edu.ustc.site.enhance.SearchEnhance;

public class SearchAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	private String keyword,wkplace,cpnm;
	public String getWkplace() {
		return wkplace;
	}

	public void setWkplace(String wkplace) {
		this.wkplace = wkplace;
	}

	public String getCpnm() {
		return cpnm;
	}

	public void setCpnm(String cpnm) {
		this.cpnm = cpnm;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	private int count;
	public String search() throws UnsupportedEncodingException{
		keyword=getParameter("keyword");
		wkplace=getParameter("wkplace");
		cpnm=getParameter("cpnm");
		setCount(new SearchEnhance(keyword,wkplace,cpnm).getCount());
		return SUCCESS;
	}
	
	public String getKeyword()
	{
		return this.keyword;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
