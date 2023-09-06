package com.osttra.to;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	String firstName;
	String lastName;
	String email;
	String userName;
	String password;
	String className;
	String status;
	String userType;
	int attempt;
	
	public User() {
		
	}
	
	public User(String firstName, String lastName, String email, String userName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userName = userName;
	}
	
	
	
	
}
