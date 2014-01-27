package com.lyra.eartrainer.dao.client;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.lyra.eartrainer.dao.NicknameDAO;
import com.lyra.eartrainer.dto.NicknameDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ClientNicknameDAO extends JerseyClient implements NicknameDAO {
    public ClientNicknameDAO(String baseURI){
    	super(baseURI);
    }
    
    //Invokes a web service 'create' request in an attempt to create a nickname
    //Returns the body of the http response message as a string
	public String createNickname(NicknameDTO nicknameDTO) throws ClientDAOException {
		//serializing the request object into a json object
		ObjectMapper mapper = new ObjectMapper();
		String requestDataObject = "";
		try {
			requestDataObject = mapper.writeValueAsString(nicknameDTO);
		} catch (Exception e) {
			throw new ClientDAOException("There was an issue while attempting to serialize the nickname.\n" + e.getMessage());
		}
		
		//sending a typical CRUD 'create' service request by setting the Media Type (Content-Type: application/json) header 
		//and by using the HTTP "POST" verb
		ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, requestDataObject);
		responseStatusCode = clientResponse.getStatus();
		
		return handleResponsePost(clientResponse);
	}
	
	//Invokes a web service 'read' request in an attempt to get a nickname
	//Returns a Data Transfer Object (DTO) of the response representation
	public NicknameDTO readNickname(String nameOrId) throws ClientDAOException {
		//sending a typical CRUD 'read' service request by setting the Media Type in the 'Accept' (Accept: application/json) header
		//and by using the HTTP "GET" verb
		ClientResponse clientResponse = webResource.path("/" + nameOrId).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		responseStatusCode = clientResponse.getStatus();
		
		String entity = handleResponseGet(clientResponse);
		NicknameDTO nickName = null;
		
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	    	nickName = mapper.readValue(entity, NicknameDTO.class);
	    } catch (Exception e) {
			throw new ClientDAOException("There was an issue while attempting to deserialize the nickname.\n" + e.getMessage());
		}
		
		return nickName;
	}
	
	/* I don't think we need these for nickname
	public void updateNickname(){
		
	}
	
	public void deleteNickname(){
		
	}
	*/


}
