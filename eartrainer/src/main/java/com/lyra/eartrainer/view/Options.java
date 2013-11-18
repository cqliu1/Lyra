package com.lyra.eartrainer.view;

import com.lyra.eartrainer.OptionsActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
 
 public class Options extends View {
   public Options(Context context, AttributeSet attrs) {
     super(context, attrs);
   }

	public void startGame() {
		// TODO Auto-generated method stub
		OptionsActivity options = (OptionsActivity) getContext();
		options.goToGame();
	}
}
