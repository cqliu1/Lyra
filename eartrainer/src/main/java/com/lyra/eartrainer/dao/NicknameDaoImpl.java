package com.lyra.eartrainer.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;

import com.lyra.eartrainer.client.LyraHttpClient;
import com.lyra.eartrainer.client.exception.ConflictException;
import com.lyra.eartrainer.model.Nickname;

public class NicknameDaoImpl implements NicknameDao {
	private static final String NICK_FILE = "nickname.txt";
	private static final String RESOURCE_URI = "http://data-lyraeartrainer.rhcloud.com/users";
	private LyraHttpClient client = null;
	private ArrayList<DAOEventListener> listeners = null;
	private File nicknameFolder = null;
	
	public NicknameDaoImpl(File dir){
		nicknameFolder = dir;
		listeners = new ArrayList<DAOEventListener>();
	}
    
	public void storeNickname(final String nickName){
		new Thread(new Runnable(){
			public void run(){
				try {
					client = new LyraHttpClient(RESOURCE_URI);
					String entity = client.executePost("/" + nickName, null);
					if(entity != null){
						Nickname resultNickname = new Nickname(Integer.parseInt(entity.trim()), nickName);
						storeLocalNickname(resultNickname);
						fireEvent(1, resultNickname);
						return;
					}
					fireEvent(2, new DaoErrorInfo(client.getResponseStatusCode(), "Failed", null));
				} catch(ConflictException ce){
					//duplicate nickname error
					fireEvent(2, new DaoErrorInfo(client.getResponseStatusCode(), "Duplicate Nickname", ce));
				} catch(Exception e){
					//A BadRequestException or ServerErrorException or NullPointerException (maybe no id was returned in the response)
					e.printStackTrace();
					fireEvent(2, new DaoErrorInfo(client.getResponseStatusCode(), "Exception", e));
				}
			}
		}).start();
	}
	
	public Nickname getLocalNickname() throws DaoParseException {
		String rawNicknameData = "";
		FileInputStream fis = null;
		try {
			File nickFile = getNicknameFile();
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
	
	private void storeLocalNickname(Nickname nickName){
		File nickFile = getNicknameFile();
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
			System.out.println("Exception while writing local nickname file.\n" + e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(fos != null){fos.close();}
			}catch(Exception e){}
		}
	}
	
	private File getNicknameFile(){
		String filePath = nicknameFolder.getAbsoluteFile() + "/" + NICK_FILE;
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
	
	private void fireEvent(int type, Object pushObject){
		int size = listeners.size();
		for(int i = 0;i < size;i++){
			DAOEventListener listener = listeners.get(i);
			if(type == 1) //success
				listener.onSuccess(pushObject);
			else //failure
				listener.onError(pushObject);
		}
	}
	
	public void addEventListener(DAOEventListener listener){
		listeners.add(listener);
	}
	
	public void removeListener(DAOEventListener listener){
		listeners.remove(listener);
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
