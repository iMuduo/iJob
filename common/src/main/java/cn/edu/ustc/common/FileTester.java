package cn.edu.ustc.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileTester {
	private String file=null;
	public FileTester(String file){
		this.file=file;
	}
	
	public String getFilePath(){
		if(new File(file).exists())
			return file;
		return getClass().getClassLoader().getResource(file).getPath();
	}
	
	public File getFile(){
		return new File (getFilePath());
	}
	
	public FileInputStream getFileInputStream()
	{
		try {
			return new FileInputStream(getFile());
		} catch (FileNotFoundException e) {
			EasyDebug.log4j(("file"+file+"is error!"),getClass());
		}
		return null;
	}
	
	public Properties getProperties(){
		Properties props=new Properties();
		try {
			props.load(getFileInputStream());
		} catch (IOException e) {
			EasyDebug.log4j(("property:"+file+"is error!"),getClass());
		}
		return props;
	}
	
	public boolean exists(){
		return new File(file).exists();
	}
}
