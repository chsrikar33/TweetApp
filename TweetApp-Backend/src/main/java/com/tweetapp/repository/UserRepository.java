package com.tweetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.entity.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {

	public UserEntity findByEmail(String userName);	
  

}
