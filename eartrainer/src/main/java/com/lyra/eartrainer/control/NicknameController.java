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
import com.lyra.eartrainer.dao.DaoParseException;
import com.lyra.eartrainer.dao.DuplicateNicknameException;
import com.lyra.eartrainer.dao.NicknameDao;
import com.lyra.eartrainer.dao.NicknameDaoImpl;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.model.Nickname;
import com.lyra.eartrainer.view.NicknameView;

public class NicknameController extends Controller {
	private NicknameView nView;
	private Nickname nickname;
	private GamePlay game;

	public NicknameController(NickActivity nickActivity){
		super(nickActivity);
	}
	
	public void initialize(){
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//instantiating relevant model classes
		game = GamePlay.instance(); //creates initial instance of GamePlay
		
		//try to read nickname object from local file
		nickname = null;
		NicknameDao nickDao = new NicknameDaoImpl();
		try {
			nickname = nickDao.getLocalNickname(activity.getFilesDir());
		} catch(DaoParseException dpe){}
		
		//uncomment the line below in order to force re-creation of the nickname
		//nickname = null;
		
		//check if nickname exists
		if(nickname != null && nickname.getId() != -1){
			//nickname already exists so this view is not needed, transition to the next view
			System.out.println("Found Nick: " + nickname.getName() + " Loading Main Menu Screen...");
			loadNextScreen();
		}
		else {
			//loading this view
			activity.setContentView(R.layout.activity_nick);
			nView = new NicknameView(activity);
			//attaching event listeners to view widgets
			attachEvents();
		}
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
		
		//saving the nickname into the remote database
		NicknameDao nicknameDao = new NicknameDaoImpl();
		try {
			nickname = nicknameDao.storeNickname(activity.getFilesDir(), userInputNick);
		} catch(DuplicateNicknameException dne){
			System.out.println("Duplicate Nickname Detected!");
			nView.displayInvalidNickMessage();
			return;
		}
		
		if(nickname != null){
			System.out.println("Saved Nick: " + nickname.getName() + " Loading Main Menu Screen...");
			loadNextScreen();
		}
		else {
			nView.displayFailedSaveMessage();
			
			//TODO get rid of this and use a button instead
			//waiting a few seconds and then automatically loading the next screen
			new Thread(new Runnable(){
				public void run(){
					try {
						Thread.currentThread().sleep(3000);
						loadNextScreen();
					} catch(InterruptedException ie){
						return; //app was closed or another thread interrupted this one for some reason
					}
				}
			}).start();
		}
	}
	
	private void loadNextScreen(){
		game.setNickname(nickname); //sets the nickname before loading the next screen
		activity.finish(); //finish the NickActivity
		
		//begin the MainMenuActivity
		Intent mainmenu = new Intent(activity, MainMenuActivity.class);
		activity.startActivity(mainmenu);
	}
}
