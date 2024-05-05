package com.tweetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweetapp.entity.ReplyTweetEntity;

public interface ReplyTweetRepository extends JpaRepository<ReplyTweetEntity,Long> {

}
