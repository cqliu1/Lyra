package com.lyra.eartrainer.control;

import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.lyra.eartrainer.MainMenuActivity;
import com.lyra.eartrainer.NickActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.dao.DaoErrorInfo;
import com.lyra.eartrainer.dao.DaoParseException;
import com.lyra.eartrainer.dao.NicknameDao;
import com.lyra.eartrainer.dao.NicknameDaoEventListener;
import com.lyra.eartrainer.dao.NicknameDaoImpl;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.model.Nickname;
import com.lyra.eartrainer.view.NicknameView;

public class NicknameController extends Controller {
	private NicknameView nView;
	private Nickname nickname;
	private GamePlay game;
	private NicknameDao nicknameDao;

	public NicknameController(NickActivity nickActivity){
		super(nickActivity);
	}
	
	public void initialize(){
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
		//instantiating relevant model classes
		game = GamePlay.instance(); //creates initial instance of GamePlay
		
		initializeNicknameDao();
		
		//try to read nickname object from local file
		nickname = null;
		try {
			nickname = nicknameDao.getLocalNickname();
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
	
	private void initializeNicknameDao(){
		//creating instance of nickname dao
		nicknameDao = new NicknameDaoImpl(activity.getFilesDir());
		
		//attaching dao event handlers
		nicknameDao.addEventListener(new NicknameDaoEventListener(){
			public void onStoreNicknameSuccess(final Nickname nickNameObject){
				activity.runOnUiThread(new Runnable(){
					public void run(){
						handleSaveSuccess(nickNameObject);
					}
				});
			}
			public void onStoreNicknameError(final DaoErrorInfo errorObject){
				activity.runOnUiThread(new Runnable(){
					public void run(){
						handleSaveFailure(errorObject);
					}
				});
			}
		});
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
		String userInputNick = ((EditText)activity.findViewById(R.id.editNick)).getText().toString().trim();
		if(!userInputNick.equals("")){
			//saving the nickname into the remote database
			nView.beginSaveProgress();
			nicknameDao.storeNickname(userInputNick);
		}
	}
	
	private void handleSaveFailure(DaoErrorInfo errorInfo){
		//duplicate nickname code below
		nView.endSaveProgress();
		
		//duplicate nickname check
		if(errorInfo != null && ("Duplicate Nickname").equals(errorInfo.getMessage())){
			System.out.println("Duplicate Nickname Detected!");
			nView.displayInvalidNickMessage();
			return;
		}
		
		//no duplicate nickname, some other error happened
		
		if(errorInfo != null && errorInfo.getEx() != null){
			errorInfo.getEx().printStackTrace();
		}
		
		doSaveFailure();
	}
	
	private void handleSaveSuccess(Nickname nicknameObject){
		nView.endSaveProgress();
		
		nickname = nicknameObject;
		System.out.println("Saved Nick: " + nickname.getName() + " Loading Main Menu Screen...");
		
		//success
		loadNextScreen();
	}
	
	private void doSaveFailure(){
		nickname = new Nickname("Guest");
		setContinueEvent();
		nView.displayFailedSaveMessage();
	}
	
	private void setContinueEvent(){
		Button btnSubmit = (Button)activity.findViewById(R.id.btnSubmitNick);
		btnSubmit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				loadNextScreen();
			}
		});
	}
	
	private void loadNextScreen(){
		game.setNickname(nickname); //sets the nickname before loading the next screen
		activity.finish(); //finish the NickActivity
		
		//begin the MainMenuActivity
		goToActivity(MainMenuActivity.class);
	}
}
