package com.lyra.eartrainer.control;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lyra.eartrainer.MainMenuActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.view.MainMenu;

public class MainMenuController extends Controller {
        private MainMenu mmView;

        public MainMenuController(MainMenuActivity mmActivity) {
                super(mmActivity);
        }

        public void initialize() {
                // creating the view
                mmView = new MainMenu(activity,null);
                // attaching event listeners to view widgets
                attachEvents();
        }

        private void attachEvents() {
                // adding submit button click handler
                Button btnSubmit = (Button) activity.findViewById(R.id.btnToGame);
                btnSubmit.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                // fill in later
                        	mmView.showOptions();
                        }
                });
                Button btnSubmit1 = (Button) activity.findViewById(R.id.btnToSettings);
                btnSubmit1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                // fill in later
                        }
                });
                Button btnSubmit2 = (Button) activity
                                .findViewById(R.id.btnToLeaderboards);
                btnSubmit2.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                // fill in later
                        }
                });
        }
}