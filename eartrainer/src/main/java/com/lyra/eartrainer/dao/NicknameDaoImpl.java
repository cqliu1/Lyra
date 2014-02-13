package com.lyra.eartrainer.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.codehaus.jackson.map.ObjectMapper;

import com.lyra.eartrainer.client.JerseyClient;
import com.lyra.eartrainer.client.exception.ConflictException;
import com.lyra.eartrainer.model.Nickname;

public class NicknameDaoImpl implements NicknameDao {
	private static final String NICK_FILE = "nickname.txt";
	private static final String RESOURCE_URI = "http://www.whatever.com/nickname_svc";
	private JerseyClient client = null;
	
	public NicknameDaoImpl(){
		client = new JerseyClient(RESOURCE_URI);
	}
    
	public Nickname storeNickname(File dir, String nickName) throws DuplicateNicknameException {
		Nickname resultNickname = null;
		
		try {
			String entity = client.executePost("/" + nickName, null);
			resultNickname = new Nickname(Integer.parseInt(entity.trim()), nickName);
			storeLocalNickname(dir, resultNickname);
		} catch(ConflictException ce){
			throw new DuplicateNicknameException("The nickname provide was already taken by another user.");
		} catch(Exception e){
			//A BadRequestException or ServerErrorException or NullPointerException (maybe no id was returned in the response)
			e.printStackTrace();
			resultNickname = null;
		}
		
		return resultNickname;
	}
	
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
	
	private void storeLocalNickname(File dir, Nickname nickName){
		File nickFile = getNicknameFile(dir);
		writeLocalNickname(nickFile, nickName);
	}
	
	private String readLocalNickname(File file){
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
			//Not going to display to the user that it failed to read local nickname.txt file. 
			//Logging error instead
			System.out.println("Exception while reading local nickname file.\n" + e.getMessage());
			e.printStackTrace();
			result = "";
		}
		finally {
			try {
				if(fis != null){fis.close();}
			}catch(Exception e){}
		}
		
		return result;
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
}
