package com.lyra.eartrainer.control;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lyra.eartrainer.OptionsActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.view.Options;

public class OptionsController extends Controller {
	private Options optionsView;

	public OptionsController(OptionsActivity optionsActivity) {
		super(optionsActivity);
	}

	public void initialize() {
		optionsView = new Options(activity, null);
		attachEvents();
	}
	
	private void attachEvents(){
		//adding button click handlers
		Button start = (Button)activity.findViewById(R.id.startGame);
		start.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//get selected options
				optionsView.startGame();
			}
		});
	}
}
