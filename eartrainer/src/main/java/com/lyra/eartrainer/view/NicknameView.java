package com.lyra.eartrainer.view;

import android.app.Activity;
//import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lyra.eartrainer.R;
import com.lyra.eartrainer.control.LyraView;

public class NicknameView extends LyraView {
	private EditText editNick;
	private TextView textDisplay;
	private TextView textPromptNick;
	private Button btnSubmit;
	
	public NicknameView(Activity act){
		super(act);
		this.editNick = (EditText)activity.findViewById(R.id.editNick);
		this.textDisplay = (TextView)activity.findViewById(R.id.textDisplayNickname);
		this.btnSubmit = (Button)activity.findViewById(R.id.btnSubmitNick);
		this.textPromptNick = (TextView)activity.findViewById(R.id.textPromptForNick);
	}
	
	public void displayInvalidNickMessage(){
		textPromptNick.setText("The nickname \"" + editNick.getText().toString() + "\" was already taken by another user. Please use the box to enter a different nickname.");
	}
	
	public void displayFailedSaveMessage(){
		editNick.setVisibility(View.INVISIBLE);
		textDisplay.setVisibility(View.INVISIBLE);
		textPromptNick.setText("\nFailed to save nickname. Please try again later."); //\n\nThe leaderboard is currently disabled.\n\nLoading game...
		btnSubmit.setText("Continue as Guest");
	}
	
	public void beginSaveProgress(){
		btnSubmit.setEnabled(false);
		startSpinner(activity, "Saving Nickname", "Please Wait...");
	}
	
	public void endSaveProgress(){
		stopSpinner();
		btnSubmit.setEnabled(true);
	}
}
