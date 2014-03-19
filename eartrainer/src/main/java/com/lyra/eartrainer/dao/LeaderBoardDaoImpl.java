package com.lyra.eartrainer.dao;

import java.util.ArrayList;

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
	private ArrayList<LeaderBoardDaoEventListener> listeners = null;
	
	public LeaderBoardDaoImpl(){
		listeners = new ArrayList<LeaderBoardDaoEventListener>();
	}
    
	//adds the user score to the leaderboard by appending it to the database via a CRUD 'create' request
	public void addScore(LeaderBoardEntry scoreEntry) throws DaoParseException {
		//FYI: Not using AsyncTask since the background thread defined here has no need to communicate with the UI thread.
		client = new LyraHttpClient(RESOURCE_URI);
		
		//serializing the request object into a json object
		ObjectMapper mapper = new ObjectMapper();
		String scoreObject = "";
		try {
			scoreObject = mapper.writeValueAsString(scoreEntry);
		} catch (Exception e) {
			throw new DaoParseException("Failed to serialize request object.\n" + e.getMessage());
		}
		
		final String scoreJson = scoreObject;
		
		new Thread(new Runnable(){
			public void run(){
				String scoreEntity = null;
				try {
					scoreEntity = client.executePost("/", scoreJson);
				} catch(Exception e){
					//ConflictException - Not worrying about this one so much since we are appending a score entry 
					//to the remote db it shouldn't happen
					//BadRequestException / ServerErrorException are valid possible statuses
					fireEvent(LeaderBoardEvents.ADD_SCORE_FAILURE, new DaoErrorInfo(client.getResponseStatusCode(), "Failed", e));
					return;
				}

				int recordId = -1; //defaults to -1 indicating an unknown id
				try {
					recordId = Integer.parseInt(scoreEntity.trim());
				} catch(Exception e){
					//result was invalid so rec id will remain -1
					fireEvent(LeaderBoardEvents.ADD_SCORE_FAILURE, new DaoErrorInfo(client.getResponseStatusCode(), "Bad Response Data", e));
					return;
				}
				
				fireEvent(LeaderBoardEvents.ADD_SCORE_SUCCESS, new Integer(recordId));
			}
		}).start();
	}
	
	//sets the page number and pulls a set of leaderboard entries (aka leaderboard scores) from the service
	public void getScores(int pageNumber){
		this.pageNumber = pageNumber;
		
		final int reqPageNumber = pageNumber;
		new Thread(new Runnable(){
			public void run(){
				
				client = new LyraHttpClient(RESOURCE_URI);
				try {
					entity = client.executeGet("/" + reqPageNumber);
				} catch(NotFoundException ne){
					fireEvent(LeaderBoardEvents.GET_SCORE_FAILURE, new DaoErrorInfo(client.getResponseStatusCode(), "Not Found", ne));
					return;
				} catch(ServerErrorException se){
					fireEvent(LeaderBoardEvents.GET_SCORE_FAILURE, new DaoErrorInfo(client.getResponseStatusCode(), "Server Error", se));
					return;
				}
				
				entity = "{\"items\": " + entity + "}";
				
				LeaderBoard leaderboard = null;
				try {
					leaderboard = (LeaderBoard)deSerialize(LeaderBoard.class);
				} catch(DaoParseException dpe){
					fireEvent(LeaderBoardEvents.GET_SCORE_FAILURE, new DaoErrorInfo(client.getResponseStatusCode(), "Parse Error", dpe));
					return;
				}
				
				int size = leaderboard.getItems().size();
				for(int i = 0;i < size;i++){
					LeaderBoardEntry entry = leaderboard.getItems().get(i);
					entry.setId(i + ((reqPageNumber - 1) * 10) + 1);
				}
				
				fireEvent(LeaderBoardEvents.GET_SCORE_SUCCESS, leaderboard);
			}
		}).start();
	}
	
	//gets the next page of leaderboard scores
	public void getNextPage(){
		getScores(pageNumber + 1);
	}
	
	//gets the previous page of leaderboard scores, or refreshes the first page
	public void getPrevPage(){
		getScores( ((pageNumber - 1) >= 1) ? (pageNumber - 1) : 1 );
	}
	
	public int getPageNumber() {
		return this.pageNumber;
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
	
	private void fireEvent(byte eventType, Object pushObject){
		int size = listeners.size();
		for(int i = 0;i < size;i++){
			LeaderBoardDaoEventListener listener = listeners.get(i);
			try {
				if(eventType == LeaderBoardEvents.ADD_SCORE_SUCCESS)
					listener.onSaveScoreSuccess((Integer)pushObject);
				else if(eventType == LeaderBoardEvents.ADD_SCORE_FAILURE)
					listener.onSaveScoreFailure((DaoErrorInfo)pushObject);
				else if(eventType == LeaderBoardEvents.GET_SCORE_SUCCESS)
					listener.onGetScoreSuccess((LeaderBoard)pushObject);
				else if(eventType == LeaderBoardEvents.GET_SCORE_FAILURE)
					listener.onGetScoreFailure((DaoErrorInfo)pushObject);
			} catch(Exception e){
				listener.onError(new DaoErrorInfo(-1, "Failed", e));
			}
		}
	}
	
	public void addEventListener(LeaderBoardDaoEventListener listener){
		listeners.add(listener);
	}
	
	public void removeListener(LeaderBoardDaoEventListener listener){
		listeners.remove(listener);
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
