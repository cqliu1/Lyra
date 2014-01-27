package com.lyra.eartrainer.dto;

import java.util.List;

public class LoaderBoardScoreList {
	private int numResults = 0;
	private List<LeaderBoardDTO> items;

	public List<LeaderBoardDTO> getItems() {
		return items;
	}
	public void setItems(List<LeaderBoardDTO> items) {
		this.items = items;
	}
	public int getNumResults() {
		return numResults;
	}
	public void setNumResults(int numResults) {
		this.numResults = numResults;
	}
}
