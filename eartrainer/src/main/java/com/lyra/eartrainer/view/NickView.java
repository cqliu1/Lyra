package com.lyra.eartrainer.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lyra.eartrainer.R;

public class NickView {
	private EditText editNick;
	private TextView textError;
	private Button btnSubmit;
	
	private NickView(){}
	
	public NickView(Activity activity){
		this.editNick = (EditText)activity.findViewById(R.id.editNick);
		this.textError = (TextView)activity.findViewById(R.id.textNickError);
		this.btnSubmit = (Button)activity.findViewById(R.id.btnSubmitNick);
	}
	
	public void displayInvalidNickMessage(){
		textError.setVisibility(View.VISIBLE);
		textError.setText("The nickname you've entered was already taken by another user.\nPlease use the box above to enter a different nickname." + editNick.getText().toString());
	}
	
	public void displayFailedSaveMessage(){
		editNick.setVisibility(View.INVISIBLE);
		btnSubmit.setVisibility(View.INVISIBLE);
		textError.setVisibility(View.VISIBLE);
		textError.setText("Failed to save nickname. Please try again later.\n\nThe leaderboard is currently disabled.\n\nLoading game...");
	}
}
