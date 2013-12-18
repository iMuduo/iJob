package cn.edu.ustc.spider;

import java.io.IOException;
import cn.edu.ustc.spider.conf.impl.SpiderConfigure;
import cn.edu.ustc.spider.conf.impl.WebSiteFactory;
import cn.edu.ustc.spider.conf.inf.ISpiderConfigure;
import cn.edu.ustc.spider.core.Spider;

public class Program 
{
    public static void main( String[] args ) throws IOException
    {
    	ISpiderConfigure conf=new SpiderConfigure(new Program().getClass().getClassLoader().getResource("spider-conf.xml").getPath(),WebSiteFactory.getIntance());
    	Spider spider=new Spider(conf);
    	spider.get();
    }
}
