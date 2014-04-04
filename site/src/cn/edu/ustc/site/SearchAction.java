package cn.edu.ustc.site;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Map;
import cn.edu.ustc.site.base.BaseAction;
import cn.edu.ustc.site.enhance.SearchEnhance;

public class SearchAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private Map<String, String> map=new Hashtable<String,String>();

	private int count;
	public String search() throws UnsupportedEncodingException{
		for(String p:QueryParams.getParams()){
			String v=getParameter(p);
			if(v!=null)
				map.put(p, v);
		}
		setCount(new SearchEnhance(map).getCount());
		return SUCCESS;
	}
	
	public String getKeyword()
	{
		return map.get("keyword");
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public Map<String, String> getMap(){
		return map;
	}
}
