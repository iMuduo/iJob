package cn.edu.ustc.analyzer.tpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.ustc.common.EasyDebug;

public class TemplateAnalyse {
	private String tpl = null;
	private Map<String, String> result = null;

	private void prepare(String tpl) {
		this.tpl = tpl;
		this.result = new Hashtable<>();
		Pattern p = Pattern.compile("\\{\\{([^}}]+)\\}\\}");
		Matcher m = p.matcher(tpl);
		while (m.find())
			result.put(m.group(1), "");
		this.tpl = tpl.replaceAll("\\{\\{([^}}]+)\\}\\}([^{{]+|$)",
				"(?<$1>.*)$2");
	}

	public TemplateAnalyse(String tpl,Object o) {
		prepare(tpl);
	}
	
	public TemplateAnalyse(String pathname) throws IOException {
		this(new File(pathname));
	}

	public TemplateAnalyse(InputStream in) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null)
			sb.append(line.replaceAll("##.*", ""));
		prepare(sb.toString());
	}

	public TemplateAnalyse(File file) throws IOException {
		this(new FileInputStream(file));
	}

	public Map<String, String> analyse(String content) {
		Pattern p = Pattern.compile(tpl,Pattern.COMMENTS);
		Matcher m = p.matcher(content);
		while (m.find()) {
			for (String key : result.keySet())
				result.put(key, m.group(key));
		}
		return new Hashtable<>(result);
	}

	public Map<String, String> getResult() {
		return result;
	}

}
