package cn.edu.ustc.site.ajax;

import com.google.gson.Gson;

import cn.edu.ustc.site.base.BaseAction;
import cn.edu.ustc.site.base.S;
import cn.edu.ustc.site.service.HotWordService;

public class HotWordAjax extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	public String execute(){
		writer(new Gson().toJson(S.get(HotWordService.class).getHotWords(15)));
		return SUCCESS;
	}
	
	public String autoComplete(){
		writer(new Gson().toJson(S.get(HotWordService.class).getAutoCompleteList(getParameter("keyword"),10)));
		return SUCCESS;
	}
}
