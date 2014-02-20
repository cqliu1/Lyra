package com.lyra.eartrainer.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lyra.eartrainer.R;

public class NicknameView {
	private EditText editNick;
	private TextView textDisplay;
	private Button btnSubmit;
	
	private NicknameView(){}
	
	public NicknameView(Activity activity){
		this.editNick = (EditText)activity.findViewById(R.id.editNick);
		this.textDisplay = (TextView)activity.findViewById(R.id.textDisplayNickname);
		this.btnSubmit = (Button)activity.findViewById(R.id.btnSubmitNick);
	}
	
	public void displayInvalidNickMessage(){
		textDisplay.setVisibility(View.VISIBLE);
		textDisplay.setText("The nickname: \"" + editNick.getText().toString() + "\" was already taken by another user.\n\nPlease use the box above to enter a different nickname.");
	}
	
	public void displayFailedSaveMessage(){
		editNick.setVisibility(View.INVISIBLE);
		btnSubmit.setVisibility(View.INVISIBLE);
		textDisplay.setVisibility(View.VISIBLE);
		textDisplay.setText("Failed to save nickname. Please try again later.\n\nThe leaderboard is currently disabled.\n\nLoading game...");
	}
}
