package cn.edu.ustc.spider;

import java.io.DataInputStream;
import java.io.IOException;
import cn.edu.ustc.spider.conf.impl.SpiderConfigure;
import cn.edu.ustc.spider.conf.impl.WebSiteFactory;
import cn.edu.ustc.spider.conf.inf.ISpiderConfigure;
import cn.edu.ustc.spider.core.Spider;

public class Program 
{
    @SuppressWarnings("deprecation")
	public static void main( String[] args ) throws IOException//"spider-conf.xml"
    {
    	ISpiderConfigure conf=new SpiderConfigure(new Program().getClass().getClassLoader().getResource("spider-conf.xml").getPath(),WebSiteFactory.getIntance());
    	Spider.setConfigure(conf);
    	Thread t1=new Thread(new Spider());
    	Thread t2=new Thread(new Spider());
    	Thread t3=new Thread(new Spider());
    	t1.start();
    	t2.start();
    	t3.start();
    	DataInputStream di=new DataInputStream(System.in);
    	String line;
    	while(true)
    	{
    		line=di.readLine();
    		if(line.equals("stop"))
    			System.exit(0);
    	}
    }
}
