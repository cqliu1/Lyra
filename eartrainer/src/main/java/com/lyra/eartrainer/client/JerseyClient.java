package com.lyra.eartrainer.client;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lyra.eartrainer.client.exception.BadRequestException;
import com.lyra.eartrainer.client.exception.ConflictException;
import com.lyra.eartrainer.client.exception.NotFoundException;
import com.lyra.eartrainer.client.exception.ServerErrorException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
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
    
    public String executePost(String uriPath, String requestData) throws ConflictException, BadRequestException, ServerErrorException {
    	String entity = null;
    	
    	Builder webResourceBuilder = null;
    	if(uriPath != null){
    		webResourceBuilder = webResource.path(uriPath).accept(MediaType.APPLICATION_JSON);
    	}
    	else {
    		webResourceBuilder = webResource.type(MediaType.APPLICATION_JSON);
    	}
    	
    	//Setting the Media Type (Content-Type: application/json) header and using the HTTP "POST" verb
		ClientResponse clientResponse = webResourceBuilder.post(ClientResponse.class, requestData);
		responseStatusCode = clientResponse.getStatus();
		
		if(responseStatusCode == Response.Status.OK.getStatusCode() || responseStatusCode == Response.Status.CREATED.getStatusCode()){
			//200 OK, or 201 Created
			//successfully created resource
			
			entity = clientResponse.getEntity(String.class);
			
			//if the response was 201 then a location header is also expected
			//TODO Maybe delete this if we don't need it
			if(responseStatusCode == Response.Status.CREATED.getStatusCode() && clientResponse.getLocation() != null){
				location = clientResponse.getLocation().toString(); //will point to the location (url with unique-identifier of the resource @ end of it, e.g: http://restfulsvc.com/nickstore/1236543)
			}
		}
		else if(responseStatusCode == Response.Status.CONFLICT.getStatusCode()){
			//409 Conflict, the resource that it attempted to create already existed (no replace allowed)
			throw new ConflictException("409 Conflict");
		}
		else if(responseStatusCode == Response.Status.BAD_REQUEST.getStatusCode()){
			//400 Bad Request, the request entity message contained invalid information
			throw new BadRequestException("400 Bad Request");
		}
		else if(responseStatusCode == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()){
			//500 Internal Server Error, some unexpected error happened
			throw new ServerErrorException("500 Internal Server Error");
		}
		
System.out.println("entity=" + entity);
		
		return entity;
    }
    
    public String executeGet(String uriPath) throws NotFoundException, ServerErrorException {
    	String entity = null;
    	
		ClientResponse clientResponse = webResource.path(uriPath).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		responseStatusCode = clientResponse.getStatus();
		
		if(responseStatusCode == Response.Status.OK.getStatusCode()){
			//200 OK
			entity = clientResponse.getEntity(String.class);
		}
		else if(responseStatusCode == Response.Status.NOT_FOUND.getStatusCode()){
			//404 Not Found, the resource deosn't exist
			throw new NotFoundException("404 Not Found");
		}
		else if(responseStatusCode == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()){
			//500 Internal Server Error, some unexpected error happened
			throw new ServerErrorException("500 Internal Server Error");
		}
		
System.out.println("entity=" + entity);
		
		return entity;
    }
    
    /* Put is not needed
     * 
    
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
    */
    
    /* delete is not needed
     * 
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
    */
    
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
