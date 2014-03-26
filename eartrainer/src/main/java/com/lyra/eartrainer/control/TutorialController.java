package com.lyra.eartrainer.control;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

import com.lyra.eartrainer.GameActivity;
import com.lyra.eartrainer.TutorialActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.R.raw;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.model.globals.Difficulties;
import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.globals.Modes;
import com.lyra.eartrainer.model.globals.ScaleTypes;
import com.lyra.eartrainer.model.instrument.IMusicInstrument;
import com.lyra.eartrainer.model.instrument.SoundInfo;
import com.lyra.eartrainer.model.instrument.MusicInstrumentFactory;
import com.lyra.eartrainer.view.Tutorial;
import android.widget.VideoView;
import android.widget.MediaController;
import android.net.Uri;

public class TutorialController extends Controller {
	private Tutorial tutorialView;
	private VideoView video;
	private GamePlay game;
	private TutorialActivity tAct;
	
	public TutorialController(TutorialActivity tutorialActivity) {
		super(tutorialActivity);
		tAct = tutorialActivity;
	}

	public void initialize() {
        activity.setContentView(R.layout.surface_view);
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
		tutorialView = new Tutorial(activity);
		video = (VideoView)activity.findViewById(R.id.videoView1);
		video.setVideoURI(Uri.parse("android.resource://" + activity.getPackageName() + "/" + R.raw.tutorial));
    	video.setMediaController(new MediaController(tAct));
		video.requestFocus();
		video.start();
	}
	private void attachEvents() {
		
	}
	
}
