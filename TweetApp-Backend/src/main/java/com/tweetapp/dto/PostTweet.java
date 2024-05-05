package com.tweetapp.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostTweet {
	private String email;
	private String tweet;
	private LocalDateTime tweetTime;
	

	public LocalDateTime getTweetTime() {
		return tweetTime;
	}

	public void setTweetTime(LocalDateTime tweetTime) {
		this.tweetTime = tweetTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	
	public PostTweet(String email, String tweet, LocalDateTime tweetTime) {
		super();
		
		this.email = email;
		this.tweet = tweet;
		this.tweetTime = tweetTime;
	}

	public PostTweet() {
		super();
	}
	

}
