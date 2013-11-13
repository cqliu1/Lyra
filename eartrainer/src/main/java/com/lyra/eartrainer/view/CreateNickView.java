package com.lyra.eartrainer.view;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lyra.eartrainer.R;

public class CreateNickView {
	private EditText editNick;
	private TextView textError;
	
	private CreateNickView(){}
	
	public CreateNickView(Activity activity){
		this.editNick = (EditText)activity.findViewById(R.id.editNick);;
		this.textError = (TextView)activity.findViewById(R.id.textNickError);
	}
	
	public void displayInvalidNickMessage(){
		//TO DO: show error message instead of a test message and create thread to automatically hide the message after a few seconds
		textError.setVisibility(View.VISIBLE);
		textError.setText("The nickname you entered was: " + editNick.getText().toString());
	}
}
