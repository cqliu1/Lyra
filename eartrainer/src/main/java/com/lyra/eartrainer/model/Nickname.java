package com.lyra.eartrainer.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.lyra.eartrainer.R;

public class Nickname {
	private static final String NICK_FILE = "nickname.txt";
	private String name;
	
	public Nickname(){
	}
	public Nickname(String name){
		this.setName(name);
	}
	
	public void storeNickname(File dir){
		File nickFile = getNickFile(dir);
		writeLocalNick(nickFile);
	}
	
	public boolean nickExists(File dir){
		File nickFile = getNickFile(dir);
		if(!nickFile.exists()) return false;
		
		//get the nickname
		name = readLocalNick(nickFile);
		if(!name.equals("") && !name.equals("null")){
			return true;
		}

		return false;
	}
	
	private String readLocalNick(File file){
		String result = "";
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			byte[] buff = new byte[(int)file.length()];
			int read = fis.read(buff);
			if(read > 0){
				result = new String(buff);
				result = result.trim();
			}
		}
		catch(Exception e){
			//do something better
			System.out.println("Exception while reading local nickname file.\n" + e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(fis != null){fis.close();}
			}catch(Exception e){}
		}
		
		return result;
	}
	
	private void writeLocalNick(File file){
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(name.getBytes());
			fos.flush();
		}
		catch(Exception e){
			//do something better
			System.out.println("Exception while reading local nickname file.\n" + e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(fos != null){fos.close();}
			}catch(Exception e){}
		}
	}
	
	private File getNickFile(File dir){
		String filePath = dir.getAbsoluteFile() + "/" + NICK_FILE;
		File nickFile = new File(filePath);
		return nickFile;
	}
	
	//getters & setters
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
