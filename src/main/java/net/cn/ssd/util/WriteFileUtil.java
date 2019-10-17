package net.cn.ssd.util;

import java.io.*;

public class WriteFileUtil {
	public static synchronized boolean writeContent(String path, String content, boolean isAppend, String charset){
		File file =  new File(path);
		boolean result =false;
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new OutputStreamWriter(
				           new FileOutputStream(file,isAppend), charset));
		           bw.write(content);
		           bw.flush();
		           bw.close();
		           result =true;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
	}
	public static synchronized boolean writeContent(String path, String content, boolean isAppend){
		File file =  new File(path);
		boolean result =false;
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new OutputStreamWriter(
				           new FileOutputStream(file,isAppend), "GBK"));
		           bw.write(content);
		           bw.flush();
		           bw.close();
		           result =true;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			return result;
	}

	public static synchronized boolean writeCoverContent(String path, String content, boolean isAppend){
		File file =  new File(path);
		boolean flag =false;
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream fileOutputStream=null;
		try {
			fileOutputStream = new FileOutputStream(file,isAppend);
			fileOutputStream.write(content.getBytes("gbk"));
			fileOutputStream.close();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static String getContent(String path){
		File file =  new File(path);
		if(!file.exists()){
			return "";
		}
		StringBuffer content=new StringBuffer("");
		try {
		InputStreamReader isr;
				isr = new InputStreamReader(new FileInputStream(file), "GBK");
			BufferedReader read = new BufferedReader(isr);
			String line = null;
	        while((line = read.readLine()) != null){  
	        	content.append(line);
	        }  
	        read.close();
	        isr.close();
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return content.toString();
	}
	
	public static String getContent(String path, String charset){
		File file =  new File(path);
		if(!file.exists()){
			return "";
		}
		StringBuffer content=new StringBuffer("");
		try {
		InputStreamReader isr;
				isr = new InputStreamReader(new FileInputStream(file), charset);
			BufferedReader read = new BufferedReader(isr);
			String line = null;
	        while((line = read.readLine()) != null){  
	        	content.append(line);
	        }  
	        read.close();
	        isr.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}
}
