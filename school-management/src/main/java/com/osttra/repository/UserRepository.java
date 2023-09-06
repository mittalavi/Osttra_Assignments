package com.osttra.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.osttra.to.User;
import com.osttra.utils.DBUtils;

@Repository
public class UserRepository {

	public void add(User user) {

		try {

			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection.prepareStatement("insert into user values(?, ?, ?, ?, ?, ? ,'Inactive' ,? , 0)");

			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getUserName());
			statement.setString(5, user.getPassword());
			statement.setString(6, user.getClassName());
			statement.setString(7, user.getUserType());

			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("inside catch of add() of UserRepository...");
			e.printStackTrace();
		}
	}

	public User getUser(String username, String password) {

		User user = null;

		try {

			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection.prepareStatement("select * from user where userName = ? and password = ? and status = 'Active' ");

			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				String firstName = resultSet.getString(1);
				String lastName = resultSet.getString(2);
				String email = resultSet.getString(3);
				String className = resultSet.getString(6);
				String status = resultSet.getString(7);
				String userType = resultSet.getString(8);
				int attempt = resultSet.getInt(9);

				user = new User(firstName, lastName, email, username, password, className, status, userType , attempt);
			}
		} catch (Exception e) {
			System.out.println("inside catch of getUser(username and password) of UserRepository...");
			e.printStackTrace();
		}

		return user;
	}

	public User getUser(String username) {

		User user = null;

		try {

			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection.prepareStatement("select * from user where userName = ?");

			statement.setString(1, username);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				String firstName = resultSet.getString(1);
				String lastName = resultSet.getString(2);
				String email = resultSet.getString(3);
				String password = resultSet.getString(5);
				String className = resultSet.getString(6);
				String status = resultSet.getString(7);
				String userType = resultSet.getString(8);
				int attempt = resultSet.getInt(9);

				user = new User(firstName, lastName, email, username, password, className, status, userType , attempt);
			}
		} catch (Exception e) {
			System.out.println("inside catch of getUser(username and password) of UserRepository...");
			e.printStackTrace();
		}

		return user;
	}
	
	public List<User>  getUsers() {
		User user = null;
		List<User> users=new ArrayList<User>();

		try {

			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection.prepareStatement("select * from user");

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				String firstName = resultSet.getString(1);
				String lastName = resultSet.getString(2);
				String email = resultSet.getString(3);
				String userName = resultSet.getString(4);
				String password = resultSet.getString(5);
				String className = resultSet.getString(6);
				String status = resultSet.getString(7);
				String userType = resultSet.getString(8);
				int attempt = resultSet.getInt(9);

				user = new User(firstName, lastName, email, userName, password, className, status, userType , attempt);
				users.add(user);
			}
		} catch (Exception e) {
			System.out.println("inside catch of getUser(username and password) of UserRepository...");
			e.printStackTrace();
		}

		return users;
	}
	
	
	public List<User>  getStudents() {
		User user = null;
		List<User> students=new ArrayList<User>();

		try {

			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection.prepareStatement("select * from user where UserType = ?");
			statement.setString(1, "student");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				String firstName = resultSet.getString(1);
				String lastName = resultSet.getString(2);
				String email = resultSet.getString(3);
				String userName = resultSet.getString(4);
				String password = resultSet.getString(5);
				String className = resultSet.getString(6);
				String status = resultSet.getString(7);
				String userType = resultSet.getString(8);
				int attempt = resultSet.getInt(9);

				user = new User(firstName, lastName, email, userName, password, className, status, userType , attempt);
				students.add(user);
			}
		} catch (Exception e) {
			System.out.println("inside catch of getUser(username and password) of UserRepository...");
			e.printStackTrace();
		}

		return students;
	}

	public void update(User oldUser) {
		try {

			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection.prepareStatement(
					"update user set firstName = ?, lastName = ?,email = ?  where userName = ?");

			statement.setString(1, oldUser.getFirstName());
			statement.setString(2, oldUser.getLastName());
			statement.setString(3, oldUser.getEmail());
			statement.setString(4, oldUser.getUserName());

			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("inside catch of update of UserRepository...");
			e.printStackTrace();
		}

	}
	
	public void delete(String userName) {
		try {

			Connection connection = DBUtils.getConnection();

			PreparedStatement statement = connection.prepareStatement("delete from user where userName = ?");

			statement.setString(1, userName);

			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("inside catch of update of UserRepository...");
			e.printStackTrace();
		}

	}
	
	public void updateStatus(String userName , String userStatus) {
		try {

			Connection connection = DBUtils.getConnection();
			PreparedStatement statement;
			
			if(userStatus.equals("allow")) {
				statement = connection.prepareStatement("update user set status ='Active' where userName = ?");
			}
			else {
				statement = connection.prepareStatement("update user set status ='Inactive' where userName = ?");
			}
			
			statement.setString(1, userName);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("inside catch of update of UserRepository...");
			e.printStackTrace();
		}

	}
	
	public void setLoginAttempts(String userName , int noOfAttempts) {
		try {

			Connection connection = DBUtils.getConnection();
			PreparedStatement statement;
			
			
			statement = connection.prepareStatement("update user set attempt = ? where userName = ?");
			
			statement.setInt(1, noOfAttempts);
			statement.setString(2, userName);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("inside catch of update of UserRepository...");
			e.printStackTrace();
		}

	}
	
}
