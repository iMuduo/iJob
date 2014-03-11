package cn.edu.ustc.site;

import java.io.UnsupportedEncodingException;

import cn.edu.ustc.site.base.BaseAction;
import cn.edu.ustc.site.enhance.SearchEnhance;

public class SearchAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	private String keyword;
	private int count;
	public String search() throws UnsupportedEncodingException{
		keyword=getParameter("keyword");
		setCount(new SearchEnhance(keyword).getCount());
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
