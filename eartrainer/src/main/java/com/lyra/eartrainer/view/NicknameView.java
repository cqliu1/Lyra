package com.lyra.eartrainer.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lyra.eartrainer.R;

public class NicknameView {
	private Activity activity;
	private EditText editNick;
	private TextView textDisplay;
	private TextView textPromptNick;
	private Button btnSubmit;
	private ProgressDialog spinner;
	
	private NicknameView(){}
	
	public NicknameView(Activity activity){
		this.activity = activity;
		this.editNick = (EditText)activity.findViewById(R.id.editNick);
		this.textDisplay = (TextView)activity.findViewById(R.id.textDisplayNickname);
		this.btnSubmit = (Button)activity.findViewById(R.id.btnSubmitNick);
		this.textPromptNick = (TextView)activity.findViewById(R.id.textPromptForNick);
	}
	
	public void beginSaveProgress(){
		btnSubmit.setEnabled(false);
		
	    spinner = new ProgressDialog(activity);
	    spinner.setCancelable(false);
	    spinner.setMessage("Please Wait...");
	    spinner.setTitle("Saving Nickname");
	    spinner.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	    spinner.setProgress(0);
	    spinner.setMax(100);
	    spinner.show();
	}
	
	public void endSaveProgress(){
		spinner.dismiss();
		btnSubmit.setEnabled(true);
	}
	
	public void displayInvalidNickMessage(){
		textPromptNick.setText("The nickname: \"" + editNick.getText().toString() + "\" was already taken by another user. Please use the box to enter a different nickname.");
	}
	
	public void displayFailedSaveMessage(){
		editNick.setVisibility(View.INVISIBLE);
		textDisplay.setVisibility(View.INVISIBLE);
		textPromptNick.setText("\nFailed to save nickname. Please try again later."); //\n\nThe leaderboard is currently disabled.\n\nLoading game...
		btnSubmit.setText("Continue as Guest");
	}
}
