package cn.edu.ustc.site.ajax;

import cn.edu.ustc.site.base.BaseAction;
import cn.edu.ustc.site.enhance.SearchEnhance;

public class SearchAjax extends BaseAction {
	private static final long serialVersionUID = 1756055658352396036L;

	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	private int index;

	public String execute(){
		writer(new SearchEnhance(getKeyword()).getJson(getIndex()));
		return SUCCESS;
	}


}
