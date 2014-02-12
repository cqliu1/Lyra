package com.lyra.eartrainer.dao.client;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.lyra.eartrainer.dao.LyraDAO;
import com.lyra.eartrainer.dao.exception.BadRequestException;
import com.lyra.eartrainer.dao.exception.ConflictException;
import com.lyra.eartrainer.dao.exception.DaoParseException;
import com.lyra.eartrainer.dao.exception.NotFoundException;
import com.lyra.eartrainer.dao.exception.ServerErrorException;

public class ClientLyraDAO implements LyraDAO {
	private static final String RESOURCE_URI = "http://www.whatever.com/nickname_svc";
	private JerseyClient client = null;
	
	public ClientLyraDAO(){
		client = new JerseyClient(RESOURCE_URI);
	}
    
    //Invokes a web service 'create' request in an attempt to create a nickname
    //Returns the body of the http response message as a string
	public String create(Object dataTransferObject) throws DaoParseException, ConflictException, BadRequestException, ServerErrorException {
		//serializing the request object into a json object
		ObjectMapper mapper = new ObjectMapper();
		String requestDataObject = "";
		try {
			requestDataObject = mapper.writeValueAsString(dataTransferObject);
		} catch (Exception e) {
			throw new DaoParseException("Failed to serialize request object.\n" + e.getMessage());
		}
		
		//sending a typical CRUD 'create' service request
		String entity = client.executePost(requestDataObject);
		
		if(client.getResponseStatusCode() == Response.Status.CONFLICT.getStatusCode()){
			//409 Conflict, the resource that it attempted to create already existed (no replace allowed)
			throw new ConflictException("Duplicate detected");
		}
		else if(client.getResponseStatusCode() == Response.Status.BAD_REQUEST.getStatusCode()){
			//400 Bad Request, the request entity message contained invalid information
			throw new BadRequestException("Bad request object");
		}
		else if(client.getResponseStatusCode() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()){
			//500 Internal Server Error, some unexpected error happened
			throw new ServerErrorException("Unexpected server error");
		}
		
		return entity;
	}
	
	public Object update(Object dataTransferObject, Class<?> transferObjectClass){
		return null;
	}
	
	//Invokes a web service 'read' request in an attempt to get a nickname
	//Returns a Data Transfer Object (DTO) of the response representation
	public Object read(String identifier, Class<?> transferObjectClass) throws DaoParseException, NotFoundException, ServerErrorException {
		//sending a typical CRUD 'read' service request by setting the Media Type in the 'Accept' (Accept: application/json) header
		//and by using the HTTP "GET" verb
		String entity = client.executeGet("/" + identifier);

		if(client.getResponseStatusCode() == Response.Status.NOT_FOUND.getStatusCode()){
			//404 Not Found, the resource deosn't exist
			throw new NotFoundException("Not Found");
		}
		else if(client.getResponseStatusCode() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()){
			//500 Internal Server Error, some unexpected error happened
			throw new ServerErrorException("Unexpected server error");
		}
		
		Object responseObject = null;
		
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	    	responseObject = mapper.readValue(entity, transferObjectClass);
	    } catch (Exception e) {
			throw new DaoParseException("Failed to deserialize response object.\n" + e.getMessage());
		}
	    
	    return responseObject;
	}
	
	public ArrayList readAll(){
		return null;
	}
}
