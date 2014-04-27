package com.lyra.eartrainer.client;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import android.os.StrictMode;

import com.lyra.eartrainer.client.exception.BadRequestException;
import com.lyra.eartrainer.client.exception.ConflictException;
import com.lyra.eartrainer.client.exception.NotFoundException;
import com.lyra.eartrainer.client.exception.ServerErrorException;

public class LyraHttpClient {
	public static final String REQUEST_GET = "GET";
	public static final String REQUEST_POST = "POST";
	//The following two connection settings don't apply to name lookups -- no way to manage timeouts for name lookups
	private static final int CONNECT_TIMEOUT = 5; //number of seconds to wait for connection before timing out
	private static final int REQUEST_TIMEOUT = 10; //number of seconds to wait for response after sending request before timing out
    private String baseURI; //the http url (including path) to the nickname service
    private int responseStatusCode;
    private DefaultHttpClient client;
    
    public LyraHttpClient(String baseURI){
    	this.baseURI = baseURI;
    	
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 1000 * CONNECT_TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, 1000 * REQUEST_TIMEOUT);
    	client = new DefaultHttpClient(params);
    	
    	//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
   		//StrictMode.setThreadPolicy(policy); 
    }
    
    public String executePost(String uriPath, String requestData) throws ConflictException, BadRequestException, ServerErrorException {
    	String body = null;
    	String uri = baseURI + uriPath;
    	
    	HttpPost post = new HttpPost(uri);
    	HttpResponse response = null;
    	
    	try {
			if(requestData != null){
		    	StringEntity requestEntity = new StringEntity(requestData, HTTP.UTF_8);
		    	requestEntity.setContentType("application/json");
		    	post.setEntity(requestEntity);
			}

			response = client.execute(post);
			responseStatusCode = response.getStatusLine().getStatusCode();

			if(responseStatusCode == ClientStatus.OK || responseStatusCode == ClientStatus.CREATED){
				//200 OK, or 201 Created
				//successfully created resource
				StringBuilder responseBody = getResponseBody(response);
				body = responseBody.toString();
			}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    	finally {
    		shutdown(response);
    	}
    	
		if(responseStatusCode == ClientStatus.CONFLICT){
			//409 Conflict, the resource that it attempted to create already existed (no replace allowed)
			throw new ConflictException("409 Conflict");
		}
		else if(responseStatusCode == ClientStatus.BAD_REQUEST){
			//400 Bad Request, the request entity message contained invalid information
			throw new BadRequestException("400 Bad Request");
		}
		else if(responseStatusCode == ClientStatus.INTERNAL_SERVER_ERROR){
			//500 Internal Server Error, some unexpected error happened
			throw new ServerErrorException("500 Internal Server Error");
		}
    	
    	return body;
    }
    
    public String executeGet(String uriPath) throws NotFoundException, ServerErrorException {
    	String body = null;
    	String uri = baseURI + uriPath;
    	HttpGet get = new HttpGet(uri);
    	
    	HttpResponse response =  null;
    	try {
    		get.setHeader("Accept","application/json");
    		response = client.execute(get);
			responseStatusCode = response.getStatusLine().getStatusCode();
			
			if(responseStatusCode == ClientStatus.OK){
				//200 OK
				StringBuilder responseBody = getResponseBody(response);
				body = responseBody.toString();
			}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	finally {
    		shutdown(response);
    	}
    	
		if(responseStatusCode == ClientStatus.NOT_FOUND){
			//404 Not Found, the resource deosn't exist
			throw new NotFoundException("404 Not Found");
		}
		else if(responseStatusCode == ClientStatus.INTERNAL_SERVER_ERROR){
			//500 Internal Server Error, some unexpected error happened
			throw new ServerErrorException("500 Internal Server Error");
		}
    	
    	return body;
    }
    
    private StringBuilder getResponseBody(HttpResponse response) throws Exception {
		StringBuilder responseBody = new StringBuilder("");
		HttpEntity entity = response.getEntity();
		if(entity.isStreaming()){
			InputStream is = entity.getContent();
			int len = -1;
			byte[] buf = new byte[1024];
			while ((len = is.read(buf)) != -1){
				responseBody.append(new String(buf, 0, len));
			}
		}
		return responseBody;
    }
    
    private void shutdown(HttpResponse response){
		try {
			//No EntityUtils.consume() method for Android because Android sucks!
			if(response != null && response.getEntity() != null){
				HttpEntity entity = response.getEntity();
				if(entity.isStreaming() && entity.getContent() != null){
					entity.getContent().close();
				}
			}
		} catch(Exception e){}
		finally {
			client.getConnectionManager().shutdown();
		}
    }
    
    public int getResponseStatusCode(){
    	return responseStatusCode;
    }
}
