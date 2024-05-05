package com.tweetapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dto.AddTweetResponse;
import com.tweetapp.dto.AllTweetResponse;
import com.tweetapp.dto.DeleteTweet;
import com.tweetapp.dto.PostTweet;
import com.tweetapp.dto.TweetListResponse;
import com.tweetapp.dto.TweetResponse;
import com.tweetapp.dto.UserResponse;
import com.tweetapp.entity.ReplyTweetEntity;
import com.tweetapp.entity.TweetsEntity;
import com.tweetapp.service.TweetAppServiceImpl;
import com.tweetapp.utility.JWTUtility;

@CrossOrigin("*")
@RestController
public class TweetsController {

	@Autowired
	TweetAppServiceImpl tweetAppService;

	@Autowired
	private TweetAppServiceImpl userService;

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	private static final String TOPIC = "DeleteTopic";

	@GetMapping("/api/v1.0/tweets/{email}") // TweetEntity
	public ResponseEntity<?> viewTweets(@PathVariable String email,
			@RequestHeader(name = "Authorization") String Token) {
		String token = Token.substring(7);
		UserDetails user = userService.loadUserByUsername(jwtUtility.getUsernameFromToken(token));
		try {
			if (jwtUtility.validateToken(token, user)) {
				TweetListResponse tweet = new TweetListResponse();
				tweet.setTweetList(tweetAppService.viewTweets(email));
				return new ResponseEntity<>(tweet, HttpStatus.OK);
			} else
				return new ResponseEntity<>("Token Not Verified", HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return new ResponseEntity<>("Token Expired", HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping("/api/v1.0/tweets/all") // AllTweetResponse
	public ResponseEntity<?> viewAllUsersTweets(@RequestHeader(name = "Authorization") String Token) {
		String token = Token.substring(7);
		UserDetails user = userService.loadUserByUsername(jwtUtility.getUsernameFromToken(token));
		try {
			if (jwtUtility.validateToken(token, user)) {
				AllTweetResponse allTweets = new AllTweetResponse();
				allTweets.setTweetList(tweetAppService.viewAllUsersTweets());
				return new ResponseEntity<>(allTweets, HttpStatus.OK);
			} else
				return new ResponseEntity<>("Token Not Verified", HttpStatus.FORBIDDEN);

		} catch (Exception e) {
			return new ResponseEntity<>("Token Expired", HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping("/api/v1.0/tweets/users/all")
	public ResponseEntity<?> viewAllUsers(@RequestHeader(name = "Authorization") String Token) {

		String token = Token.substring(7);
		UserDetails user = userService.loadUserByUsername(jwtUtility.getUsernameFromToken(token));
		try {
			if (jwtUtility.validateToken(token, user)) {
				return new ResponseEntity<>(tweetAppService.UsersInTweetApp(), HttpStatus.OK);
			} else
				return new ResponseEntity<>("Token Not Verified", HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return new ResponseEntity<>("Token Expired", HttpStatus.FORBIDDEN);
		}

	}

	@PostMapping("/api/v1.0/tweets/{email}/add")
	public ResponseEntity<?> postATweet(@PathVariable String email, @RequestBody PostTweet postTweet,
			@RequestHeader(name = "Authorization") String Token) {
		String token = Token.substring(7);
		UserDetails user = userService.loadUserByUsername(jwtUtility.getUsernameFromToken(token));
		try {
			if (jwtUtility.validateToken(token, user)) {
				return new ResponseEntity<>(tweetAppService.postATweet(email, postTweet), HttpStatus.OK);
			} else
				return new ResponseEntity<>("Token Not Verified", HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return new ResponseEntity<>("Token Expired", HttpStatus.FORBIDDEN);
		}
	}

	@PutMapping("/api/v1.0/tweets/{email}/update/{id}")
	public ResponseEntity<?> updateTweet(@PathVariable long id, @RequestBody PostTweet postTweet,
			@RequestHeader(name = "Authorization") String Token) {
		String token = Token.substring(7);
		UserDetails user = userService.loadUserByUsername(jwtUtility.getUsernameFromToken(token));
		try {
			if (jwtUtility.validateToken(token, user)) {
				return new ResponseEntity<>(tweetAppService.updateTweet(id, postTweet.getTweet()), HttpStatus.OK);
			} else
				return new ResponseEntity<>("Token Not Verified", HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return new ResponseEntity<>("Token Expired", HttpStatus.FORBIDDEN);
		}

	}

	@DeleteMapping("/api/v1.0/tweets/{email}/delete/{id}")
	public ResponseEntity<?> deleteTweet(@PathVariable long id, @RequestHeader(name = "Authorization") String Token) {
		String token = Token.substring(7);
		UserDetails user = userService.loadUserByUsername(jwtUtility.getUsernameFromToken(token));
		try {
			if (jwtUtility.validateToken(token, user)) {
				return new ResponseEntity<>(tweetAppService.deleteTweet(id), HttpStatus.OK);
			} else
				return new ResponseEntity<>("Token Not Verified", HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return new ResponseEntity<>("Token Expired", HttpStatus.FORBIDDEN);
		}

	}

	/*
	 * @DeleteMapping("/api/v1.0/tweets/{email}/delete/{id}") public void
	 * deleteTweet(@PathVariable String id) { //DeleteTweet
	 * deleteTweet=tweetAppService.deleteTweet(id); kafkaTemplate.send(TOPIC,id); }
	 */

	@PutMapping("/api/v1.0/tweets/{email}/like/{id}")
	public void likeTweet(@PathVariable long id, @RequestHeader(name = "Authorization") String Token) {
		String token = Token.substring(7);
		UserDetails user = userService.loadUserByUsername(jwtUtility.getUsernameFromToken(token));
		if (jwtUtility.validateToken(token, user))
			tweetAppService.likeTweet(id);
	}

	@PutMapping("/api/v1.0/tweets/{email}/dislike/{id}")
	public void disLikeTweet(@PathVariable long id, @RequestHeader(name = "Authorization") String Token) {
		String token = Token.substring(7);
		UserDetails user = userService.loadUserByUsername(jwtUtility.getUsernameFromToken(token));
		if (jwtUtility.validateToken(token, user))
			tweetAppService.disLikeTweet(id);
	}

	@PostMapping("/api/v1.0/tweets/{email}/reply/{id}")
	public ResponseEntity<?> replyTweet(@PathVariable long id, @PathVariable String email,
			@RequestBody ReplyTweetEntity replyTweetEntity, @RequestHeader(name = "Authorization") String Token) {
		String token = Token.substring(7);
		UserDetails user = userService.loadUserByUsername(jwtUtility.getUsernameFromToken(token));

		try {
			if (jwtUtility.validateToken(token, user))
				return new ResponseEntity<>(tweetAppService.replyTweet(id, email, replyTweetEntity), HttpStatus.OK);
			else
				return new ResponseEntity<>("Token Not Verified", HttpStatus.FORBIDDEN);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Token Expired", HttpStatus.FORBIDDEN);
		}
	}

}
