package cn.edu.ustc.analyzer.tpl;

import java.io.IOException;

import cn.edu.ustc.analyzer.Program;

public final class TemplateAnalyseFactory {
	private static TemplateAnalyse chinahr, zhaopin, job51;

	public static TemplateAnalyse geTemplateAnalyse(String tpl) throws IOException {
		switch (tpl) {
		case "chinahr":
			if(chinahr==null)
				chinahr= new TemplateAnalyse(new Program().getClass().getClassLoader().getResourceAsStream("chinahr.tpl"));
			return chinahr;
		case "zhaopin":
			if(zhaopin==null)
				zhaopin= new TemplateAnalyse(new Program().getClass().getClassLoader().getResourceAsStream("zhaopin.tpl"));
			return zhaopin;
		case "51job":
			if(job51==null)
				job51= new TemplateAnalyse(new Program().getClass().getClassLoader().getResourceAsStream("51job.tpl"));
			return job51;
		}
		return null;
	}

}
