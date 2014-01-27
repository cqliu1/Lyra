package com.lyra.eartrainer.dao.client;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.lyra.eartrainer.dto.NicknameDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class JerseyClient {
    protected WebResource webResource;
    protected Client client;
    protected String location;
    protected String baseURI; //the http url (including path) to the nickname service
    protected int responseStatusCode;
    
    public JerseyClient(String baseURI){
    	this.baseURI = baseURI;
    	client = Client.create(new DefaultClientConfig());
    	webResource = client.resource(baseURI);
    }
    
    protected String handleResponsePost(ClientResponse clientResponse){
		String entity = null;
		
		if(responseStatusCode == Response.Status.OK.getStatusCode() || responseStatusCode == Response.Status.CREATED.getStatusCode()){
			//200 OK, or 201 Created
			//successfully created resource
			
			entity = clientResponse.getEntity(String.class);
			
			//if the response was 201 then a location header is also expected
			if(responseStatusCode == Response.Status.CREATED.getStatusCode()){
				location = clientResponse.getLocation().toString(); //will point to the location (url with unique-identifier of the resource @ end of it, e.g: http://restfulsvc.com/nickstore/1236543)
			}
		}
		else if(responseStatusCode == Response.Status.CONFLICT.getStatusCode()){
			//409 Conflict, the resource that it attempted to create already existed (no replace allowed)
			//TODO: DO whatever here
		}
		else if(responseStatusCode == Response.Status.BAD_REQUEST.getStatusCode()){
			//400 Bad Request, the request entity message contained invalid information
			//TODO: whatever
		}
		else if(responseStatusCode == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()){
			//500 Internal Server Error, some unexpected error happened
			//TODO: whatever
		}
		
System.out.println("entity=" + entity);
		
		return entity;
    }
    
    protected String handleResponseGet(ClientResponse clientResponse){
    	String entity = null;
    	
		if(responseStatusCode == Response.Status.OK.getStatusCode()){
			//200 OK
			entity = clientResponse.getEntity(String.class);
		}
		else if(responseStatusCode == Response.Status.NOT_FOUND.getStatusCode()){
			//404 Not Found, the resource deosn't exist
			//TODO: whatever
		}
		else if(responseStatusCode == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()){
			//500 Internal Server Error, some unexpected error happened
			//TODO: whatever
		}
	
System.out.println("entity=" + entity);
		
		return entity;
    }
    
    protected String handleResponsePut(ClientResponse clientResponse){
    	String entity = null;
    	
		if(responseStatusCode == Response.Status.OK.getStatusCode() || responseStatusCode == Response.Status.CREATED.getStatusCode()){
			//200 OK, or 201 Created
			//successfully created resource
			entity = clientResponse.getEntity(String.class);
			
			//if the response was 201 then a location header is also expected
			if(responseStatusCode == Response.Status.CREATED.getStatusCode()){
				location = clientResponse.getLocation().toString(); //will point to the location (url with unique-identifier of the resource @ end of it, e.g: http://restfulsvc.com/nickstore/1236543)
			}
		}
		else if(responseStatusCode == Response.Status.CONFLICT.getStatusCode()){
			//409 Conflict, update failed, not allowed to make changes to the resource right now
			//TODO: DO whatever here
		}
		else if(responseStatusCode == Response.Status.NOT_FOUND.getStatusCode()){
			//404 Not Found, the resource deosn't exist
			//TODO: whatever
		}
		else if(responseStatusCode == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()){
			//500 Internal Server Error, some unexpected error happened
			//TODO: whatever
		}
		else if(responseStatusCode == Response.Status.NO_CONTENT.getStatusCode()){
			//204 No Content, do nothing - no response body
		}
		
System.out.println("entity=" + entity);
    	
    	return entity;
    }
    
    protected boolean handleResponseDelete(ClientResponse clientResponse){
		if(responseStatusCode == Response.Status.NO_CONTENT.getStatusCode()){
			//204 No Content, indication of a successful delete
			return true;
		}
		else if(responseStatusCode == Response.Status.NOT_FOUND.getStatusCode()){
			//404 Not Found, the resource deosn't exist
			//TODO: whatever
		}
		else if(responseStatusCode == 405){
			//405 Method Not Allowed - indicates your not allowed to delete the resource at this time
			//TODO: whatever
		}
		else if(responseStatusCode == Response.Status.SERVICE_UNAVAILABLE.getStatusCode()){
			//503 Service Unavailable, the service has disabled or is unable to handle any delete requests right now, try again later
			//TODO: whatever
		}
		else if(responseStatusCode == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()){
			//500 Internal Server Error, some unexpected error happened
			//TODO: whatever
		}
    	
    	return false;
    }
    
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getBaseURI() {
		return baseURI;
	}
	public void setBaseURI(String baseURI) {
		this.baseURI = baseURI;
	}
	public int getResponseStatusCode() {
		return responseStatusCode;
	}
	public void setResponseStatusCode(int responseStatusCode) {
		this.responseStatusCode = responseStatusCode;
	}
}
