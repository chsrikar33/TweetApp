package com.tweetapp.dto;

import java.util.List;

public class TweetListResponse {
	
	private List<TweetResponse> tweetList;

	public List<TweetResponse> getTweetList() {
		return tweetList;
	}

	public void setTweetList(List<TweetResponse> tweetList) {
		this.tweetList = tweetList;
	}

	public TweetListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
}
