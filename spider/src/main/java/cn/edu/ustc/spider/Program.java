package cn.edu.ustc.spider;

import java.io.IOException;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;

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
//    	Document document=Jsoup.connect("http://search.51job.com/jobsearch/search_result.php?fromJs=1&jobarea=000000%2C00&district=000000&funtype=0000&industrytype=00&issuedate=9&providesalary=99&keyword=2&keywordtype=2&lang=c&stype=2&postchannel=0001&workyear=99&cotype=99&degreefrom=99&jobterm=01&lonlat=0%2C0&radius=-1&ord_field=0&list_type=0&fromType=14&curr_page=1").get();
//    	Elements a=document.getElementsByTag("a");
//    	for(Element e:a)
//    		System.out.println(e.attr("href"));
    }
}
