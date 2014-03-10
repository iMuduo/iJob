package cn.edu.ustc.site;

import java.io.IOException;
import java.io.PrintWriter;
import cn.edu.ustc.site.base.BaseAction;

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

	public String execute() throws IOException{
		getResponse().setContentType ("text/html;charset=utf-8");
		PrintWriter writer=getResponse().getWriter();
		writer.println(new SearchEnhance(getKeyword()).getJson(getIndex()));
		writer.flush();
		writer.close();
		return SUCCESS;
	}


}
