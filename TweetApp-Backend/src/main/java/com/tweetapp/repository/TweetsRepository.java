package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.dto.TweetResponse;
import com.tweetapp.entity.TweetsEntity;
@Repository
public interface TweetsRepository extends JpaRepository<TweetsEntity,Long> {
	  
	    public List<TweetsEntity> findByEmail(String email);
	    //  public List<TweetResponse> findByEmail(String email);

}
