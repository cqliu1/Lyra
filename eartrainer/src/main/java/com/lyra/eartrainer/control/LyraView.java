package com.lyra.eartrainer.control;

import android.app.Activity;
import android.app.ProgressDialog;

public class LyraView {
	protected ProgressDialog spinner;
	protected Activity activity;
	
	public LyraView(Activity activity){
		this.activity = activity;
	}
	
	public void startSpinner(Activity activity, String title, String message){
		spinner = new ProgressDialog(activity);
	    spinner.setCancelable(false);
	    spinner.setMessage(message);
	    spinner.setTitle(title);
	    spinner.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	    spinner.setProgress(0);
	    spinner.setMax(100);
	    spinner.show();
	}
	
	public void stopSpinner(){
		if(spinner != null){
			spinner.dismiss();
		}
	}
}
