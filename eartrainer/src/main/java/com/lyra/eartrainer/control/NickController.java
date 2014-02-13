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
import com.lyra.eartrainer.dao.DuplicateNicknameException;
import com.lyra.eartrainer.dao.NicknameDao;
import com.lyra.eartrainer.dao.NicknameDaoImpl;
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
		
		/* Save this - Temporarily commented out for testing against the service
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
		
		//saving the nickname into the remote database
		NicknameDao nicknameDao = new NicknameDaoImpl();
		try {
			nickname = nicknameDao.storeNickname(activity.getFilesDir(), userInputNick);
		} catch(DuplicateNicknameException dne){
			nView.displayInvalidNickMessage();
			return;
		}
		
		if(nickname != null){
			System.out.println("Saved Nick: " + nickname.getName() + " Loading Main Menu Screen...");
			loadNextScreen();
		}
		else {
			nView.displayFailedSaveMessage();
			nickname = new Nickname("Guest");
			
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
		game.setNickname(nickname.getName()); //sets the nickname before loading the next screen
		activity.finish(); //finish the NickActivity
		
		//begin the MainMenuActivity
		Intent mainmenu = new Intent(activity, MainMenuActivity.class);
		activity.startActivity(mainmenu);
	}
}
