package cn.edu.ustc.site.base;

import java.util.Hashtable;
import cn.edu.ustc.site.service.BaseService;

public class S{
	private static final Hashtable<String, BaseService> container=new Hashtable<String, BaseService>();
	@SuppressWarnings("unchecked")
	public static <T extends BaseService> T get(Class<T> service){
		String serviceName=service.getName();
		if(!container.contains(serviceName))
			try {
				container.put(serviceName, (BaseService) service.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		return (T)container.get(serviceName);
	}
}
