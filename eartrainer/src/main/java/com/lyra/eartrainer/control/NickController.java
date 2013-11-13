package com.lyra.eartrainer.control;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lyra.eartrainer.NickActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.view.CreateNickView;

public class NickController extends Controller {
	private CreateNickView cnView;
	 
	public NickController(NickActivity nickActivity){
		super(nickActivity);
	}
	
	public void initialize(){
		//creating the view
		cnView = new CreateNickView(activity);
		//attaching event listeners to view widgets
		attachEvents();
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
		//TO DO:
		//check and make sure that the nick doesn't already exist in the leaderboard db
		
		//if the nick didn't exist, save it and move on
		
		//else display invalid nick message
		cnView.displayInvalidNickMessage(); //using the view to update itself
	}
}
