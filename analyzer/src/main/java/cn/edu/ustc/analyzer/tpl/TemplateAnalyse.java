package cn.edu.ustc.analyzer.tpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.ustc.common.EasyDebug;

public class TemplateAnalyse {
	private Map<String, Pattern> patterns = null;

	private void prepare(List<String> list) {
		Pattern p = Pattern.compile("\\{\\{([^}}]+)\\}\\}");
		for (String i : list) {
			Matcher m = p.matcher(i);
			String field = "";
			if (m.find())
				field = m.group(1);
			Pattern pattern = Pattern.compile(i.replaceAll(
					"\\{\\{([^}}]+)\\}\\}([^{{]+|$)", "(?<$1>.*?)$2"),
					Pattern.DOTALL | Pattern.MULTILINE
							| Pattern.CASE_INSENSITIVE);
			patterns.put(field, pattern);
		}

	}

	public TemplateAnalyse(String pathname) throws IOException {
		this(new File(pathname));
	}

	public TemplateAnalyse(InputStream in) throws IOException {

		this.patterns = new Hashtable<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;
		List<String> list = new LinkedList<String>();
		while ((line = br.readLine()) != null)
			list.add((line.replaceAll("##.*", "")));
		prepare(list);
	}

	public TemplateAnalyse(File file) throws IOException {
		this(new FileInputStream(file));
	}

	public Map<String, String> analyseContent(String content) {
		
		Hashtable<String, String> result=new Hashtable<>();
		
		for(String key :patterns.keySet())
		{
			Matcher m=patterns.get(key).matcher(content);
			String value="";
			if(m.groupCount()>2)
				EasyDebug.debug("Template tags error！"+patterns.get(key).pattern());
			if(m.find())
				value=m.group(1);
			result.put(key, value);
		}
		
		return result;
	}

}
