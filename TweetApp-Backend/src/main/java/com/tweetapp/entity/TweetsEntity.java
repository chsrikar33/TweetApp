package com.tweetapp.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/*@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter*/
@Entity
public class TweetsEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String email;
	private String tweet;
	private int likes;
	private int dislikes;
	private LocalDateTime tweetTime;
	
	@OneToMany(targetEntity=ReplyTweetEntity.class,cascade=CascadeType.ALL)
	@JoinColumn(name="tweet_id",referencedColumnName="id")
	private List<ReplyTweetEntity> reply;
	
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
	public List<ReplyTweetEntity> getReply() {
		return reply;
	}

	public void setReply(List<ReplyTweetEntity> reply) {
		this.reply = reply;
	}
	
	

	public LocalDateTime getTweetTime() {
		return tweetTime;
	}

	public void setTweetTime(LocalDateTime tweetTime) {
		this.tweetTime = tweetTime;
	}

	public TweetsEntity() {
		super();
	}

	@Override
	public String toString() {
		return "TweetsEntity [id=" + id + ", email=" + email + ", tweet=" + tweet + ", likes=" + likes + ", dislikes="
				+ dislikes + ", tweetTime=" + tweetTime + ", reply=" + reply + "]";
	}

	
	
	

}
