package cn.edu.ustc.spider;

import java.util.LinkedList;

import cn.edu.ustc.common.EasyDebug;

public final class ProgramConfigure {
	public static  void makeConf(String[] confs)
	{
		LinkedList<String> confList=new LinkedList<String>();
		for(int i=0;i<confs.length;i++)
			confList.add(confs[i]);
		if(confList.contains("-debug"))
			EasyDebug.setOn();
	}
}
