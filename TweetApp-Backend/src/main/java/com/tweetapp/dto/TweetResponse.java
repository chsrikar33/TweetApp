package com.tweetapp.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.tweetapp.entity.ReplyTweetEntity;
import com.tweetapp.entity.TweetsEntity;

public class TweetResponse {

	/*private List<TweetsEntity> tweetList;

	public List<TweetsEntity> getTweetList() {
		return tweetList;
	}

	public void setTweetList(List<TweetsEntity> tweetList) {
		this.tweetList = tweetList;
	}

	public TweetResponse() {
		super();
	}

	@Override
	public String toString() {
		return "TweetResponse [tweetList=" + tweetList + "]";
	}*/
	
	private long id;
	private String email;
	private String tweet;
	private int likes;
	private int dislikes;
	private LocalDateTime tweetTime;
	private List<ReplyTweetEntity> reply;
	private List<TweetResponse> tweetList;
	private String duration;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	public LocalDateTime getTweetTime() {
		return tweetTime;
	}
	public void setTweetTime(LocalDateTime tweetTime) {
		this.tweetTime = tweetTime;
	}
	public List<ReplyTweetEntity> getReply() {
		return reply;
	}
	public void setReply(List<ReplyTweetEntity> reply) {
		this.reply = reply;
	}
	public List<TweetResponse> getTweetList() {
		return tweetList;
	}
	public void setTweetList(List<TweetResponse> list) {
		this.tweetList = list;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "TweetResponse [id=" + id + ", email=" + email + ", tweet=" + tweet + ", likes=" + likes + ", dislikes="
				+ dislikes + ", tweetTime=" + tweetTime + ", reply=" + reply + ", tweetList=" + tweetList + "]";
	}
	public TweetResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TweetResponse(long id, String email, String tweet, int likes, int dislikes, LocalDateTime tweetTime,
			List<ReplyTweetEntity> reply) {
		super();
		this.id = id;
		this.email = email;
		this.tweet = tweet;
		this.likes = likes;
		this.dislikes = dislikes;
		this.tweetTime = tweetTime;
		this.reply = reply;
	}
	

	
	
	
	
	
	
}
