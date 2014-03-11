package cn.edu.ustc.site.base;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public final class PyHelper {
	@SuppressWarnings("deprecation")
	public static String getSimplePy(String s){
		try {
			HanyuPinyinOutputFormat hf=new HanyuPinyinOutputFormat();
			hf.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			return PinyinHelper.toHanyuPinyinString(s, hf, "").replaceAll(" ", "");
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return s;
	}
}
