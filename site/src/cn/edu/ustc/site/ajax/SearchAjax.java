package cn.edu.ustc.site.ajax;

import java.util.Hashtable;
import java.util.Map;

import cn.edu.ustc.site.QueryParams;
import cn.edu.ustc.site.base.BaseAction;
import cn.edu.ustc.site.enhance.SearchEnhance;

public class SearchAjax extends BaseAction {
	private static final long serialVersionUID = 1756055658352396036L;
	private Map<String, String> map = new Hashtable<String, String>();

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	private int index;

	public String execute() {
		for (String p : QueryParams.getParams()) {
			String v = getParameter(p);
			if (v != null)
				map.put(p, v);
		}
		writer(new SearchEnhance(map).getJson(getIndex()));
		return SUCCESS;
	}
}
