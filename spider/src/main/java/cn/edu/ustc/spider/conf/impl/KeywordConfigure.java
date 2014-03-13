package cn.edu.ustc.spider.conf.impl;

import java.util.List;
import cn.edu.ustc.common.FileSmart;
import cn.edu.ustc.spider.conf.inf.IKeywordConfigure;

public class KeywordConfigure implements IKeywordConfigure{
	private final List<String> keywords=new FileSmart("keywords.txt").getList();
	@Override
	public String getKeyword(int i) {
		if(i>keywords.size())
			return "";
		return keywords.get(i);
	}

	@Override
	public int getCount() {
		return keywords.size();
	}

}
