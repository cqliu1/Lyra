package com.lyra.eartrainer.model;

import java.util.ArrayList;

public class Instrument {
	protected int scaleType;
	protected String name; 			//The name of the instrument (e.g. piano, guitar)
	protected ArrayList<Note> Notes;	//Collection of notes to be played
	protected int minNote;
	protected int maxNote;
}
