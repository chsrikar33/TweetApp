package com.tweetapp.dto;

import java.util.List;

public class UserResponse {
	List<String> userList;

	public List<String> getUserList() {
		return userList;
	}

	public void setUserList(List<String> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "UserResponse [userList=" + userList + "]";
	}
	
	

}
