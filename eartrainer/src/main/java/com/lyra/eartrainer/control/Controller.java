package com.lyra.eartrainer.control;

import com.lyra.eartrainer.OptionsActivity;

import android.app.Activity;
import android.content.Intent;

public class Controller {
	protected Activity activity; 
	
	private Controller(){}
	
	public Controller(Activity activity){
		this.activity = activity;
	}
	
	protected void goToActivity(Class<?> activityType){
		//activity.finish();
		Intent intent = new Intent(activity, activityType);
		activity.startActivity(intent);
	}
}
