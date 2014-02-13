package com.lyra.eartrainer.model;

import java.util.List;

public class LeaderBoard {
	private int numResults = 0;
	private List<LeaderBoardEntry> items;

	public List<LeaderBoardEntry> getItems() {
		return items;
	}
	public void setItems(List<LeaderBoardEntry> items) {
		this.items = items;
	}
	public int getNumResults() {
		return numResults;
	}
	public void setNumResults(int numResults) {
		this.numResults = numResults;
	}
}
