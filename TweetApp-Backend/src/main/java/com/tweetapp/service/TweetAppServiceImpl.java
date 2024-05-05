package com.tweetapp.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.tweetapp.repository.UserRepository;
import com.tweetapp.dto.DeleteTweet;
import com.tweetapp.dto.PostTweet;
import com.tweetapp.dto.TweetResponse;
import com.tweetapp.dto.UserResponse;
import com.tweetapp.entity.ReplyTweetEntity;
import com.tweetapp.entity.TweetsEntity;
import com.tweetapp.entity.UserEntity;
import com.tweetapp.exception.UserAlreadyExistException;
import com.tweetapp.repository.ReplyTweetRepository;
import com.tweetapp.repository.TweetsRepository;

@Service
public class TweetAppServiceImpl implements TweetAppService,UserDetailsService  {
	@Autowired
	UserRepository userRepository;
	@Autowired
	TweetsRepository tweetRepository;
	@Autowired
	ReplyTweetRepository replyTweetRepository;
	//
	 @Override
	    public UserDetails loadUserByUsername(String userName) {

		UserEntity userCredintals = userRepository.findByEmail(userName);
		String email=userCredintals.getEmail();
		String password=userCredintals.getPassword();

	        return new User(email,password,new ArrayList<>());
	    }

	public void storeData(String email, String lastName, String gender, LocalDate date, String firstName,
			String password) throws UserAlreadyExistException {
		Optional<UserEntity> user = userRepository.findById(email);
		if (user.isEmpty()) {
			UserEntity tweet = new UserEntity(email, lastName, gender, date, firstName, password);
			userRepository.save(tweet);
		} else
			throw new UserAlreadyExistException("This email is already registered");

	}

	public boolean validateEmail(String email) {
		Optional<UserEntity> tweet = userRepository.findById(email);
		if (tweet.isPresent())
			return false;
		else
			return true;
	}

	public boolean checkCredentials(String email, String password) {
		Optional<UserEntity> tweet = userRepository.findById(email);
		if (tweet.isPresent()) {
			if (tweet.get().getPassword().equals(password))
				return true;
			else
				return false;
		} else
			return false;

	}

	public String resetPassword(String userName, String password) {

		Optional<UserEntity> reset = userRepository.findById(userName);
		if (reset.isPresent()) {
			UserEntity tweet1 = reset.get();
			tweet1.setPassword(password);
			userRepository.save(tweet1);
			return "Password Reset Completed";
		} else {
			return "Entered User not present in the Database";
		}

	}

	public boolean forgotPassword(String email, String newPassword, LocalDate date, String firstName) {
		Optional<UserEntity> forget = userRepository.findById(email);
		if (forget.isPresent()) {
			if (forget.get().getDate().equals(date) && forget.get().getFirstName().equals(firstName)) {
				UserEntity tweet1 = forget.get();
				tweet1.setPassword(newPassword);
				userRepository.save(tweet1);
				return true;
			} else
				return false;

		} else {
			return false;
		}
	}

	public String checkOldPassword(String email, String oldPassword) {
		Optional<UserEntity> reset = userRepository.findById(email);
		if (reset.isPresent()) {
			if (reset.get().getPassword().equals(oldPassword)) {
				return "matched";
			} else {
				return "old password not matched";
			}
		}
		return "user not exist";

	}

	public UserResponse UsersInTweetApp() {
		List<UserEntity> viewAllUsers = userRepository.findAll();
		List<String> list=new ArrayList<String>();
		UserResponse allUsers=new UserResponse();
		viewAllUsers.forEach(tweet -> list.add(tweet.getEmail()));
		allUsers.setUserList(list);
		return allUsers;
	}

	
	public long postATweet(String email, PostTweet postTweet) {
		TweetsEntity tweetPost = new TweetsEntity();
		tweetPost.setEmail(email);
		tweetPost.setTweet(postTweet.getTweet());
		tweetPost.setTweetTime(LocalDateTime.now());
		System.out.println(LocalDateTime.now());
		tweetRepository.save(tweetPost);
		return (tweetPost.getId());
	}

	/*public List<TweetsEntity> viewTweets(String email) {
		List<TweetsEntity> viewTweets = tweetRepository.findByEmail(email);
		LocalDateTime presentTime = LocalDateTime.now();
		if (viewTweets.size() > 0) {
			viewTweets.forEach(tweets->{
				LocalDateTime tweetTime=tweets.getTweetTime();
				Duration duration = Duration.between(tweetTime, presentTime);
				if(duration.getSeconds()<=60)
				System.out.println("Secs :"+duration.getSeconds());
				else if(duration.toMinutes()<=60) 
				System.out.println("Mins :"+duration.toMinutes());
				else if(duration.toHours()<=24) 
				System.out.println("Hours :"+duration.toHours());
				else
				System.out.println("Days :"+duration.toDays());
				
			});
			return viewTweets;
		}
		else
			System.out.println("No tweets for the user");
		return null;
	}*/
	
	
	public List<TweetResponse> setDurationTweet(List<TweetsEntity> viewTweets)
	{
		LocalDateTime presentTime = LocalDateTime.now();
		List<TweetResponse> newList=viewTweets.stream().map(tweets->{
			TweetResponse tweetResponse=new TweetResponse(tweets.getId(),tweets.getEmail(),tweets.getTweet(),tweets.getLikes(),
					tweets.getDislikes(),tweets.getTweetTime(),tweets.getReply());
			LocalDateTime tweetTime=tweets.getTweetTime();
			Duration duration = Duration.between(tweetTime, presentTime);
			if(duration.getSeconds()<=60) 
			tweetResponse.setDuration(duration.getSeconds()+"secs ago");
			else if(duration.toMinutes()<=60) 
			tweetResponse.setDuration(duration.toMinutes()+"mins ago");
			else if(duration.toHours()<=24) 
			tweetResponse.setDuration(duration.toHours()+"hrs ago");
			else 
			tweetResponse.setDuration(duration.toDays()+"days ago");
			return tweetResponse;
			
		}).collect(Collectors.toList());
		return newList;
	}
	
	public List<TweetResponse> viewTweets(String email) {
		List<TweetsEntity> viewTweets = tweetRepository.findByEmail(email);
		
		if (viewTweets.size() > 0) {
			
			return setDurationTweet(viewTweets);
		}
		else
			System.out.println("No tweets for the user");
		return null;
	}

	  /*public List<TweetsEntity> viewAllUsersTweets() {

		List<TweetsEntity> viewAllTweets = tweetRepository.findAll();
		if (viewAllTweets.size() > 0)
			return viewAllTweets;
		// viewAllTweets.forEach(tweet -> System.out.println(tweet));
		else
			System.out.println("No tweets");
		return null;

	}*/
	 public List<TweetResponse> viewAllUsersTweets() {
	  
	  List<TweetsEntity> viewAllTweets = tweetRepository.findAll();
		if (viewAllTweets.size() > 0)
		return setDurationTweet(viewAllTweets);
		else
			System.out.println("No tweets");
		return null;

	}

	public TweetsEntity updateTweet(long id, String tweet) {
		Optional<TweetsEntity> list = tweetRepository.findById(id);
		if (list.isPresent()) {
			TweetsEntity updateTweet = list.get();
			updateTweet.setTweet(tweet);
			return tweetRepository.save(updateTweet);
		} else
			System.out.println("No tweets associated with this " + id);
		return null;

	}

	public DeleteTweet deleteTweet(long id) {
		Optional<TweetsEntity> list = tweetRepository.findById(id);
		if (list.isPresent()) {
			TweetsEntity tweetEntity=list.get();
			DeleteTweet deleteTweet=new DeleteTweet();
			deleteTweet.setId(tweetEntity.getId());
			deleteTweet.setTweet(tweetEntity.getTweet());
			tweetRepository.deleteById(id);
			return deleteTweet;
		} else {
			System.out.println("No tweets associated with this " + id);
			return null;
		}
	}
	
	
	/*@KafkaListener(topics = "DeleteTopic", groupId = "group-id")
	   public void listen(String id) {
	      System.out.println("Received ID in group - group-id: " + id);
	      long kafkaId=Long.valueOf(id);
			tweetRepository.deleteById(kafkaId);
	   }*/

	public void likeTweet(long id) {
		Optional<TweetsEntity> list = tweetRepository.findById(id);
		if (list.isPresent()) {
			TweetsEntity likeTweet = list.get();
			int likes = likeTweet.getLikes();
			likeTweet.setLikes(++likes);
			tweetRepository.save(likeTweet);
			System.out.println(likes);

		} else
			System.out.println("No tweets associated with this " + id);
	}

	public void disLikeTweet(long id) {
		Optional<TweetsEntity> list = tweetRepository.findById(id);
		if (list.isPresent()) {
			TweetsEntity disLikeTweet = list.get();
			int disLikes = disLikeTweet.getDislikes();
			disLikeTweet.setDislikes(++disLikes);
			tweetRepository.save(disLikeTweet);
			System.out.println(disLikes);

		} else
			System.out.println("No tweets associated with this " + id);
	}

	

	public boolean replyTweet(long id, String email, ReplyTweetEntity replyTweetEntity) {
		Optional<TweetsEntity> list = tweetRepository.findById(id);
		if (list.isPresent()) {
			TweetsEntity replyTweet1 = list.get();
			List<ReplyTweetEntity> replyList = replyTweet1.getReply();
			replyList.add(replyTweetEntity);
			replyTweet1.setReply(replyList);
			tweetRepository.save(replyTweet1);
			return true;
		} else {
			System.out.println("No tweets associated with this " + id);
			return false;
		}
	}



	

}
