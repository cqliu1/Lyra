package com.lyra.eartrainer.control;

import android.app.Activity;
import android.app.ProgressDialog;

public class LyraView {
	protected ProgressDialog spinner;
	protected Activity activity;
	protected boolean isSpinnerSpinning;
	
	public LyraView(Activity activity){
		this.activity = activity;
		isSpinnerSpinning = false;
	}
	
	public void startSpinner(Activity activity, String title, String message){
		if(!isSpinnerSpinning){
			isSpinnerSpinning = true;
			spinner = new ProgressDialog(activity);
		    spinner.setCancelable(false);
		    spinner.setMessage(message);
		    spinner.setTitle(title);
		    spinner.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		    spinner.setProgress(0);
		    spinner.setMax(100);
		    spinner.show();
		}
	}
	
	public void stopSpinner(){
		if(spinner != null){
			spinner.dismiss();
		}
		isSpinnerSpinning = false;
	}
}
