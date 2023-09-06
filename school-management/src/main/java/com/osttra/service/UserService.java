package com.osttra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osttra.repository.UserRepository;
import com.osttra.to.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void register(User user) {

		userRepository.add(user);
	}

	public User login(String username, String password) {

		return userRepository.getUser(username, password);
	}

	public User getUser(String username) {

		User user = userRepository.getUser(username);

		return user;
	}

	public void update(User user) {

		userRepository.update(user);
	}
	
	public void updateStatus(String userName , String userStatus) {

		userRepository.updateStatus(userName , userStatus);
	}

	public List<User> getUsers() {

		return userRepository.getUsers();
	}

	public List<User> getStudents() {
		return userRepository.getStudents();
	}


	public void delete(String userName) {

		userRepository.delete(userName);
	}

	public void setLoginAttempts(String userName , int noOfAttempts) {
		userRepository.setLoginAttempts(userName , noOfAttempts);
		
	}


}
