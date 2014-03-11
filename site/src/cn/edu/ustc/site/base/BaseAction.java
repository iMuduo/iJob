package cn.edu.ustc.site.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	private static final long serialVersionUID = 2753551262455036803L;
	
	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) ActionContext.getContext().get(
				ServletActionContext.HTTP_RESPONSE);
	}
	
	protected HttpServletRequest getRequest(){
		return (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
	}
	
	protected String getParameter(String key){
			try {
				return new String(getRequest().getParameter(key).getBytes("utf-8"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "";
	}
	
	protected void writer(String content) {
		getResponse().setContentType ("text/html;charset=utf-8");
		PrintWriter writer;
		try {
			writer = getResponse().getWriter();
			writer.println(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
