package com.lyra.eartrainer.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.codehaus.jackson.map.ObjectMapper;

import com.lyra.eartrainer.client.LyraHttpClient;
import com.lyra.eartrainer.client.exception.ConflictException;
import com.lyra.eartrainer.model.Nickname;

public class NicknameDaoImpl implements NicknameDao {
	private static final String NICK_FILE = "nickname.txt";
	private static final String RESOURCE_URI = "http://data-lyraeartrainer.rhcloud.com/users";
	private LyraHttpClient client = null;
	
	public NicknameDaoImpl(){
		client = new LyraHttpClient(RESOURCE_URI);
	}
    
	public Nickname storeNickname(File dir, String nickName) throws DuplicateNicknameException {
		Nickname resultNickname = null;
		
		try {
			String entity = client.executePost("/" + nickName, null);
			if(entity != null){
				resultNickname = new Nickname(Integer.parseInt(entity.trim()), nickName);
				storeLocalNickname(dir, resultNickname);
			}
		} catch(ConflictException ce){
			throw new DuplicateNicknameException("The nickname provide was already taken by another user.");
		} catch(Exception e){
			//A BadRequestException or ServerErrorException or NullPointerException (maybe no id was returned in the response)
			e.printStackTrace();
			resultNickname = null;
		}
		
		return resultNickname;
	}
	
	public Nickname getLocalNickname(File dir) throws DaoParseException {
		String rawNicknameData = "";
		FileInputStream fis = null;
		try {
			File nickFile = getNicknameFile(dir);
			fis = new FileInputStream(nickFile);
			byte[] buff = new byte[(int)nickFile.length()];
			int read = fis.read(buff);
			if(read > 0){
				rawNicknameData = new String(buff);
				rawNicknameData = rawNicknameData.trim();
			}
		}
		catch(FileNotFoundException fnfe){} //suppress error message
		catch(Exception e){
			//Not going to display to the user that it failed to read local nickname.txt file. 
			//Logging error instead
			System.out.println("Exception while reading local nickname file.\n" + e.getMessage());
			e.printStackTrace();
			rawNicknameData = "";
		}
		finally {
			try {
				if(fis != null){fis.close();}
			}catch(Exception e){}
		}

		Nickname nickname = (Nickname)deSerialize(rawNicknameData, Nickname.class);
		return nickname;
	}
	
	private void storeLocalNickname(File dir, Nickname nickName){
		File nickFile = getNicknameFile(dir);
		writeLocalNickname(nickFile, nickName);
	}
	
	private void writeLocalNickname(File file, Nickname nickName){
		FileOutputStream fos = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			String nicknameObject = mapper.writeValueAsString(nickName);
			fos = new FileOutputStream(file);
			fos.write(nicknameObject.getBytes());
			fos.flush();
		}
		catch(Exception e){
			System.out.println("Exception while reading local nickname file.\n" + e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(fos != null){fos.close();}
			}catch(Exception e){}
		}
	}
	
	private File getNicknameFile(File dir){
		String filePath = dir.getAbsoluteFile() + "/" + NICK_FILE;
		File nickFile = new File(filePath);
		return nickFile;
	}
	
	//deserializes json
	private Object deSerialize(String json, Class<?> valueType) throws DaoParseException {
		Object result = null;
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	    	result = mapper.readValue(json, valueType);
	    } catch (Exception e) {
			throw new DaoParseException("Failed to deserialize response object.\n" + e.getMessage());
		}
	    return result;
	}
	
	/*
	public boolean nicknameExists(File dir){
		File nickFile = getNicknameFile(dir);
		if(!nickFile.exists()) return false;
		
		//get the nickname
		String localData = readLocalNickname(nickFile);
		if(!localData.equals("") && !localData.equals("null")){
			return true;
		}

		return false;
	}
	*/
}
