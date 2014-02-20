package cn.edu.ustc.spider;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;


import cn.edu.ustc.spider.conf.impl.SpiderConfigure;
import cn.edu.ustc.spider.conf.impl.WebSiteFactory;
import cn.edu.ustc.spider.conf.inf.ISpiderConfigure;
import cn.edu.ustc.spider.core.Spider;

public class Program {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException
			, ClassNotFoundException {
		//Make configure
		ProgramConfigure.makeConf(args);
		//Load configure
		String confFilePath=null;
		if(new File("spider-conf.xml").exists())
			confFilePath="spider-conf.xml";
		else 
			confFilePath=new Program().getClass().getClassLoader().getResource("spider-conf.xml").getPath();
		ISpiderConfigure conf = new SpiderConfigure(confFilePath,
				WebSiteFactory.getIntance());
		
		//Judge Status
		if (Spider.hasStatus())
			Spider.getStatus();
		else
			Spider.setConfigure(conf);
		
		for(int i=0;i<conf.getSiteCount();i++)
			new Thread(new Spider()).start();

		DataInputStream di = new DataInputStream(System.in);
		String line;
		while (true) {
			line = di.readLine();
			if (line.equals("stop")) {
				Spider.save();
				System.exit(0);
			}
		}
	}
}
