package com.tweetapp.dto;

import java.util.List;
public class AllTweetResponse {
	
	List<TweetResponse> tweetList;
	
	
	

	public List<TweetResponse> getTweetList() {
		return tweetList;
	}

	public void setTweetList(List<TweetResponse> tweetList) {
		this.tweetList = tweetList;
	}

	public AllTweetResponse() {
		super();
	}

	@Override
	public String toString() {
		return "TweetResponse [tweetList=" + tweetList + "]";
	}

}
