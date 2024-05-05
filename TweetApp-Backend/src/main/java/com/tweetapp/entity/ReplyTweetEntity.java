package com.tweetapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReplyTweetEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long replyId;
	private String email;
	private String reply;
	private String replyFrom;

	public Long getReplyId() {
		return replyId;
	}

	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getReplyFrom() {
		return replyFrom;
	}

	public void setReplyFrom(String replyFrom) {
		this.replyFrom = replyFrom;
	}

	@Override
	public String toString() {
		return "ReplyTweetEntity [replyId=" + replyId + ", email=" + email + ", reply=" + reply + ", replyFrom="
				+ replyFrom + "]";
	}

	
}
