package com.lyra.eartrainer.dao.client;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.lyra.eartrainer.dao.LeaderBoardDAO;
import com.lyra.eartrainer.dto.LeaderBoardDTO;
import com.lyra.eartrainer.dto.LoaderBoardScoreList;
import com.sun.jersey.api.client.ClientResponse;

public class ClientLeaderBoardDAO extends JerseyClient implements LeaderBoardDAO {
    public ClientLeaderBoardDAO(String baseURI){
    	super(baseURI);
    }
    
    //Invokes a web service 'read' request in an attempt to get a score object for the current user
    public LeaderBoardDTO getScore(String identifier) throws ClientDAOException {
		//sending a CRUD 'read' service request by setting the Media Type in the 'Accept' (Accept: application/json) header
		//and by using the HTTP "GET" verb
		ClientResponse clientResponse = webResource.path("/" + identifier).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		responseStatusCode = clientResponse.getStatus();
		
		String entity = handleResponseGet(clientResponse);
		LeaderBoardDTO lbScore = null;
		
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	    	lbScore = mapper.readValue(entity, LeaderBoardDTO.class);
	    } catch (Exception e) {
			throw new ClientDAOException("There was an issue while attempting to deserialize the score record.\n" + e.getMessage());
		}
	    
	    return lbScore;
    }
    
    //Invokes a web service 'read' request in an attempt to get all scores
    public LoaderBoardScoreList getAllScores() throws ClientDAOException {
		//sending a CRUD 'read' service request by setting the Media Type in the 'Accept' (Accept: application/json) header
		//and by using the HTTP "GET" verb
		ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		responseStatusCode = clientResponse.getStatus();
		String entity = handleResponseGet(clientResponse);
		LoaderBoardScoreList scoreList = null;
		
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	    	scoreList = mapper.readValue(entity, LoaderBoardScoreList.class);
	    } catch (Exception e) {
			throw new ClientDAOException("There was an issue while attempting to deserialize the score records.\n" + e.getMessage());
		}
	    
	    return scoreList;
    }
    
    //Invokes a web service 'create/update' request in an attempt to append a new score to the existing list of scores stored in the DB
    //Returns the body of the http response message as a string
    public void addScore(LeaderBoardDTO leaderboardScoreObject) throws ClientDAOException {
		//serializing the request object into a json object
		ObjectMapper mapper = new ObjectMapper();
		String requestDataObject = "";
		try {
			requestDataObject = mapper.writeValueAsString(leaderboardScoreObject);
		} catch (Exception e) {
			throw new ClientDAOException("There was an issue while attempting to serialize the leaderboard score object.\n" + e.getMessage());
		}
		
		//sending a CRUD 'update' service request by setting the Media Type (Content-Type: application/json) header 
		//and by using the HTTP "POST" verb
		//Note: POST is used since a score is being appended, not replaced. If the score was replaced I would have used "PUT" by convention.
		ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, requestDataObject);
		responseStatusCode = clientResponse.getStatus();
		
		String entity = handleResponsePost(clientResponse);
		
		//TODO whatever comes next here
    }
}
