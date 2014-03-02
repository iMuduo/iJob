package cn.edu.ustc.site;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import cn.edu.ustc.common.MongoDB;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("rawtypes")
public class SearchAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Hashtable<String, String>> result=null;
	private final String[] fields=new String[]{"cpnm","jbnm","cpscale","cptype","cptrade","genderrq","rcnos","agerq","wkform","deadline","degreerq","salary","wkxp","wkplace","jbdesc","wkrq","benefit","date","cpinfo"};
	private String keyword;
	public String search() throws UnsupportedEncodingException{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
		request.setCharacterEncoding("gb2312");
		keyword=request.getParameter("keyword");
		result=new LinkedList<Hashtable<String,String>>();
		DBCollection db=MongoDB.getCollection("jobInfo2");
		BasicDBObject conditon=new BasicDBObject();
		conditon.put("jbnm",  Pattern.compile(String.format("^.*%s.*$", keyword), Pattern.CASE_INSENSITIVE));
		DBCursor query=db.find(conditon);
		while(query.hasNext())
		{
			Hashtable<String, String> row=new Hashtable<String, String>();
			DBObject obj=query.next();
			for(int i=0;i<fields.length;i++)
				row.put(fields[i], (String) (obj.get(fields[i]).equals("")?"²»Ïê":obj.get(fields[i])));
			result.add(row);
		}
		return SUCCESS;
	}
	
	public List getResult()
	{
		return result;
	}
	
	public String getKeyword()
	{
		return this.keyword;
	}
}
