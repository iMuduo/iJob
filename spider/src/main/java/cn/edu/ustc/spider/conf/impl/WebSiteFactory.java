package cn.edu.ustc.spider.conf.impl;

import cn.edu.ustc.spider.conf.inf.IWebSite;
import cn.edu.ustc.spider.conf.inf.IWebSiteFactory;

public final class WebSiteFactory implements IWebSiteFactory{
	private static IWebSiteFactory instance=null;
	private WebSiteFactory(){}
	
	@Override
	public IWebSite createWebSite(String name) {
		switch (name.toLowerCase()) {
		case "51job": return new WebSite();
		default: return new WebSite();		
		}
	}
	
	public static IWebSiteFactory getIntance()
	{
		if(instance==null)
			instance=new WebSiteFactory();
		return instance;
	}
}
