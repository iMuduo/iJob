package cn.edu.ustc.common.net;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import cn.edu.ustc.common.EasyDebug;

public final class UrlFetcher {
	private final static CloseableHttpClient client = HttpClients.createDefault();
	public String fetch(String url) {
		String html="";
		HttpEntity entity=null;
		CloseableHttpResponse response=null;
		try {
			response=client.execute(new HttpGet(url));
			int status=response.getStatusLine().getStatusCode();
			if(status>=200 && status <300)
				entity=response.getEntity();
			else
				throw new IOException("fetch "+url+"status"+status);
			html=EntityUtils.toString(entity,"gb2312");
		} catch (IOException e) {
			e.printStackTrace();
			EasyDebug.log4j("fetch "+url+"error", getClass());
		}finally{
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
				EasyDebug.log4j("respone "+url+"error", getClass());
			}
		}
		return html;
	}
	
	
	public String fetch(String scheme,String host,String path,Map<String, String> data){
		HttpGet get=null;
		try {
			URIBuilder builder=new URIBuilder().setScheme(scheme).setHost(host).setPath(path);
			for(String key:data.keySet())
				builder.setParameter(key, data.get(key));
			get=new HttpGet(builder.build());
				
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return fetch(get.getURI().toString());
	}
}
