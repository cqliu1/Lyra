package com.lyra.eartrainer.control;

import android.content.Intent;

import com.lyra.eartrainer.MainActivity;
import com.lyra.eartrainer.NickActivity;

public class MainController extends Controller {

	public MainController(MainActivity mainActivity){
		super(mainActivity);
	}
	
	public void initialize(){
        //use model to check and see if nickname exists
        //if one doesn't then select the nickname screen
        Intent nickname = new Intent(activity, NickActivity.class);
        activity.startActivity(nickname);
	}
}
