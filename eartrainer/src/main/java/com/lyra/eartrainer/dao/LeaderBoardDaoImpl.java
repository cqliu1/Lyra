package com.lyra.eartrainer.dao;

import org.codehaus.jackson.map.ObjectMapper;

import com.lyra.eartrainer.client.LyraHttpClient;
import com.lyra.eartrainer.client.exception.NotFoundException;
import com.lyra.eartrainer.client.exception.ServerErrorException;
import com.lyra.eartrainer.model.LeaderBoard;
import com.lyra.eartrainer.model.LeaderBoardEntry;

public class LeaderBoardDaoImpl implements LeaderBoardDao {
	private static final String RESOURCE_URI = "http://data-lyraeartrainer.rhcloud.com/leaderboard";
	private LyraHttpClient client = null;
	private int pageNumber = 1;
	private String entity = null;
	
	public LeaderBoardDaoImpl(){
		
	}
    
	//adds the user score to the leaderboard by appending it to the database via a CRUD 'create' request
	public int addScore(LeaderBoardEntry scoreEntry) throws DaoParseException {
		client = new LyraHttpClient(RESOURCE_URI);
		
		//serializing the request object into a json object
		ObjectMapper mapper = new ObjectMapper();
		String scoreObject = "";
		try {
			scoreObject = mapper.writeValueAsString(scoreEntry);
		} catch (Exception e) {
			throw new DaoParseException("Failed to serialize request object.\n" + e.getMessage());
		}
		
		entity = null;
		try {
			entity = client.executePost("/", scoreObject);
		} catch(Exception e){
			//ConflictException - Not worrying about this one so much since we are appending a score entry 
			//to the remote db it shouldn't happen
			//BadRequestException / ServerErrorException are valid possible statuses
			return -1;
		}
		
		int recordId = -1;
		
		try {
			recordId = Integer.parseInt(entity.trim());
		} catch(Exception e){} //result was invalid so rec id will return as -1
		
		return recordId;
	}
	
	//sets the page number and pulls a set of leaderboard entries (aka leaderboard scores) from the service
	public LeaderBoard getScores(int pageNumber) throws DaoParseException {
		this.pageNumber = pageNumber;
		
		client = new LyraHttpClient(RESOURCE_URI);
		
		entity = doGetRequest("/" + pageNumber);
		entity = "{\"items\": " + entity + "}";
		LeaderBoard leaderboard = (LeaderBoard)deSerialize(LeaderBoard.class);
		
		int size = leaderboard.getItems().size();
		for(int i = 0;i < size;i++){
			LeaderBoardEntry entry = leaderboard.getItems().get(i);
			entry.setId(i + ((pageNumber - 1) * 10) + 1);
		}
		
	    return leaderboard;
	}
	
	//gets the next page of leaderboard scores
	public LeaderBoard getNextPage() throws DaoParseException {
		return getScores(pageNumber + 1);
	}
	
	//gets the previous page of leaderboard scores, or refreshes the first page
	public LeaderBoard getPrevPage() throws DaoParseException {
		return getScores( ((pageNumber - 1) >= 1) ? (pageNumber - 1) : 1 );
	}
	
	//performs get request over uriPath
	private String doGetRequest(String uriPath){
		String entity = null;
		try {
			entity = client.executeGet(uriPath);
		} catch(NotFoundException ne){
			//TODO Thinking about what I want to do here, will decide a little later when svc is available
			return null;
		} catch(ServerErrorException se){
			return null;
		}
		return entity;
	}
	
	//deserializes json response objects into hydrated java objects of type: valueType
	private Object deSerialize(Class<?> valueType) throws DaoParseException {
		Object result = null;
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	    	result = mapper.readValue(entity, valueType);
	    } catch (Exception e) {
			throw new DaoParseException("Failed to deserialize response object.\n" + e.getMessage());
		}
	    return result;
	}
	
	/*
	public LeaderBoardEntry getScore(String identifier) throws DaoParseException {
		//sending a typical CRUD 'read' service request by setting the Media Type in the 'Accept' (Accept: application/json) header
		//and by using the HTTP "GET" verb
		entity = doGetRequest("/" + identifier);
		LeaderBoardEntry scoreEntry = (LeaderBoardEntry)deSerializeEntity(LeaderBoardEntry.class);
		

//	    ObjectMapper mapper = new ObjectMapper();
//	    try {
//	    	scoreEntry = mapper.readValue(entity, LeaderBoardEntry.class);
//	    } catch (Exception e) {
//			throw new DaoParseException("Failed to deserialize response object.\n" + e.getMessage());
//		}
	    
	    return scoreEntry;
	}
	*/
}
