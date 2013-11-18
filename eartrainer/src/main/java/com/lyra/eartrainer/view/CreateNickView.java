package com.lyra.eartrainer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lyra.eartrainer.NickActivity;
import com.lyra.eartrainer.R;

public class CreateNickView extends View{
	private EditText editNick;
	private TextView textError;
	
    public CreateNickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.editNick = (EditText) findViewById(R.id.editNick);
        this.textError = (TextView) findViewById(R.id.textNickError);
    }
	
//	public CreateNickView(Activity activity){
//		this.activity = (NickActivity) activity;
//		this.editNick = (EditText) activity.findViewById(R.id.editNick);
//		this.textError = (TextView) activity.findViewById(R.id.textNickError);
//	}
	
	public void displayInvalidNickMessage(){
		//TO DO: show error message instead of a test message and create thread to automatically hide the message after a few seconds
		textError.setVisibility(View.VISIBLE);
		textError.setText("The nickname you entered was: " + editNick.getText().toString());
	}

	public void goToMain() {
		NickActivity nick = (NickActivity) getContext();
		nick.goToMain();
	}
}
