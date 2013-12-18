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
    	ISpiderConfigure conf=new SpiderConfigure("D:\\Repository\\iJob\\iJob\\spider\\src\\main\\java\\spider-conf.xml",WebSiteFactory.getIntance());
    	Spider spider=new Spider(conf);
    	spider.get();
    }
}
