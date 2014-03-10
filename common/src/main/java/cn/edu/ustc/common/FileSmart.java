package cn.edu.ustc.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class FileSmart {
	private String file=null;
	public FileSmart(String file){
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
	
	public List<String> getList(){
		List<String> list=new LinkedList<String>();
		try {
			FileReader fr=new FileReader(getFile());
			BufferedReader br=new BufferedReader(fr);
			String line;
			while(StringHelper.isNotNull(line=br.readLine()))
			{
				if(StringHelper.isNullOrEmpty(line))
					continue;
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public boolean exists(){
		return new File(file).exists();
	}
}
