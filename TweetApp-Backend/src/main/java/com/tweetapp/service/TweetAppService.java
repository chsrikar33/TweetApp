package com.tweetapp.service;

import java.util.List;



import com.tweetapp.dto.DeleteTweet;
import com.tweetapp.dto.PostTweet;
import com.tweetapp.dto.TweetResponse;
import com.tweetapp.dto.UserResponse;
import com.tweetapp.entity.ReplyTweetEntity;
import com.tweetapp.entity.TweetsEntity;

public interface TweetAppService {

	public List<TweetResponse> viewTweets(String email);

	public List<TweetResponse> viewAllUsersTweets();

	public UserResponse UsersInTweetApp();

	public long postATweet(String email, PostTweet postTweet);

	public TweetsEntity updateTweet(long id, String tweet);

	public void likeTweet(long id);

	public void disLikeTweet(long id);

	public boolean replyTweet(long id, String email, ReplyTweetEntity replyTweetEntity);
	
}
