package com.lyra.eartrainer.control;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.lyra.eartrainer.MainMenuActivity;
import com.lyra.eartrainer.NickActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.dao.LyraDAO;
import com.lyra.eartrainer.dao.NicknameTransferObject;
import com.lyra.eartrainer.dao.client.ClientLyraDAO;
import com.lyra.eartrainer.dao.exception.BadRequestException;
import com.lyra.eartrainer.dao.exception.ConflictException;
import com.lyra.eartrainer.dao.exception.DaoParseException;
import com.lyra.eartrainer.dao.exception.ServerErrorException;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.model.Nickname;
import com.lyra.eartrainer.view.NickView;

public class NickController extends Controller {
	private NickView nView;
	private Nickname nickname;
	private GamePlay game;

	public NickController(NickActivity nickActivity){
		super(nickActivity);
	}
	
	public void initialize(){
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//instantiating relevant model classes
		nickname = new Nickname();
		game = GamePlay.instance(); //creates initial instance of GamePlay
		
		/* Save this - Temporarily commented out until this is finished
		if(nickname.localNickExists(activity.getFilesDir())){
			//nickname already exists so this view is not needed, transition to the next view
			System.out.println("Found Nick: " + nickname.getName() + " Loading Main Menu Screen...");
			loadNextScreen();
		}
		else {
		*/
			//loading this view
			activity.setContentView(R.layout.activity_nick);
			nView = new NickView(activity);
			//attaching event listeners to view widgets
			attachEvents();
		//}
	}

	private void attachEvents(){
		//adding submit button click handler
		Button btnSubmit = (Button)activity.findViewById(R.id.btnSubmitNick);
		btnSubmit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				submitNick();
			}
		});
	}
	
	//the submit button handler
	private void submitNick(){
		//attempting to create the nickname
		String userInputNick = ((EditText)activity.findViewById(R.id.editNick)).getText().toString();
		
		NicknameTransferObject nickTransferObject = new NicknameTransferObject();
		nickTransferObject.setId(0);
		nickTransferObject.setNickname(userInputNick);
		
		//saving the nickname into the remote database
		LyraDAO nickDAO = new ClientLyraDAO();
		try {
			nickDAO.create(nickTransferObject);
		}catch(DaoParseException cde){
			System.out.println("Parse Failure when creating nickname. " + cde.getMessage());
		}catch(ConflictException ce){
			System.out.println("Duplicate nickname detected. " + ce.getMessage());
		}catch(Exception e){
			//handling BadRequestException or ServerErrorException
			System.out.println("Failed to create nickname. " + e.getMessage());
		}
		
		nickname.setName(userInputNick);
		
		//The nick didn't exist, save it and move on
		nickname.storeNickname(activity.getFilesDir());
		
		if(nickname.localNickExists(activity.getFilesDir())){
			System.out.println("Saved Nick: " + nickname.getName() + " Loading Main Menu Screen...");
			loadNextScreen();
		}
		else {
			nView.displayFailedSaveMessage();
		}
	}
	
	private void loadNextScreen(){
		game.setNickname(nickname.getName()); //sets the nickname before loading the next screen
		activity.finish(); //finish the NickActivity
		
		//begin the MainMenuActivity
		Intent mainmenu = new Intent(activity, MainMenuActivity.class);
		activity.startActivity(mainmenu);
	}
}
