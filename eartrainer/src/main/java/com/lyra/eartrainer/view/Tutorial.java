package com.lyra.eartrainer.view;

import com.lyra.eartrainer.R;

import android.app.Activity;
import android.widget.VideoView;
 public class Tutorial {
	 private VideoView video;
   public Tutorial(Activity activity) {
	   video = (VideoView) activity.findViewById(R.id.videoView1);
   }
}
