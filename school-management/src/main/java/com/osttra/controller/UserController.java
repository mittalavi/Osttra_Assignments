package com.osttra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.osttra.service.UserService;
import com.osttra.to.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String index() {
		return "login";
	}

	@GetMapping("/registration")
	public String getRegistration() {
		return "register";
	}

	@PostMapping("/registration")
	public String register(User user) {

		userService.register(user);

		return "login";
	}

	@PostMapping("/login")
	public ModelAndView login(String userName, String password, HttpServletRequest request) {

		User user = userService.getUser(userName);
		ModelAndView modelAndView = null;

		if (user != null) {
			User newUser = userService.login(userName, password);
			if (newUser == null) {
				 if(user.getStatus().equals("Inactive") ) {
						modelAndView = new ModelAndView("login");
						modelAndView.addObject("errorMessage", "You are Blocked!!");
					}
				if (user.getAttempt() >= 2) {
					modelAndView = new ModelAndView("login");
					modelAndView.addObject("errorMessage", "You are Blocked!!");
					String userStatus = "block";
					userService.updateStatus(userName, userStatus);
				} else {

					modelAndView = new ModelAndView("login");
					modelAndView.addObject("errorMessage", "Wrong Credentials, please try again!!");
					userService.setLoginAttempts(userName , user.getAttempt() + 1);
				}

			} 
			
			else {
				
				userService.setLoginAttempts(userName , 0);

				modelAndView = new ModelAndView("welcome");

				HttpSession session = request.getSession();
				session.setAttribute("user", newUser);

				if (newUser.getUserType().equals("admin")) {
					List<User> users = userService.getUsers();
					modelAndView.addObject("users", users);
				} else if (newUser.getUserType().equals("teacher")) {
					List<User> students = userService.getStudents();
					modelAndView.addObject("students", students);
				}

			}

		}

		else {
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("errorMessage", "Wrong Credentials, please try again!!");
		}
		return modelAndView;
	}

	@GetMapping("/welcome")
	public ModelAndView welcome(HttpServletRequest request) {

		ModelAndView modelAndView = null;
		HttpSession session = request.getSession(false);

		if (session != null) {

			modelAndView = new ModelAndView("welcome");

			if (((User) session.getAttribute("user")).getUserType().equals("admin")) {
				List<User> users = userService.getUsers();
				modelAndView.addObject("users", users);
			} else if (((User) session.getAttribute("user")).getUserType().equals("teacher")) {
				List<User> students = userService.getStudents();
				modelAndView.addObject("students", students);
			}

		} else {
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("errorMessage", "Please Login First!!");
		}

		return modelAndView;
	}

	@PostMapping("/search")
	public ModelAndView search(String userName, HttpServletRequest request) {
		User user = userService.getUser(userName);

		ModelAndView modelAndView = null;
		HttpSession session = request.getSession(false);

		if (session != null) {
			modelAndView = new ModelAndView("search");
			modelAndView.addObject("user", user);

		} else {
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("errorMessage", "Please Login First!!");
		}

		return modelAndView;
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("login");
		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
			modelAndView.addObject("logoutMessage", "Logout successfully");
		} else {
			modelAndView.addObject("logoutErrorMessage", "You were never logged-in, please login first to logout!!");
		}

		return modelAndView;

	}

	@GetMapping("/delete/{userName}")
	public ModelAndView delete(@PathVariable String userName, HttpServletRequest request) {

		ModelAndView modelAndView = null;
		HttpSession session = request.getSession(false);

		if (session != null) {
			userService.delete(userName);

			if (((User) session.getAttribute("user")).getUserType().equals("admin")) {
				List<User> users = userService.getUsers();
				modelAndView = new ModelAndView("welcome");
				modelAndView.addObject("users", users);
			}

			else {
				modelAndView = new ModelAndView("login");
				modelAndView.addObject("deleteSucessMsg", "Your Account has been deleted successfully..");
			}
		} else {

			modelAndView = new ModelAndView("login");
			modelAndView.addObject("errorMessage", "You are not authenticated, please login first!!");
		}

		return modelAndView;
	}

	@GetMapping("/update/{UserName}")
	public ModelAndView updatePage(@PathVariable String UserName, HttpServletRequest request) {
		
		ModelAndView modelAndView = null;
		HttpSession session = request.getSession(false);
		User deletingUser = userService.getUser(UserName);
		
		if (session != null) {
			modelAndView = new ModelAndView("update");
			modelAndView.addObject("deletingUser", deletingUser);
		} else {
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("errorMessage", "Please Login First!!");
		}

		return modelAndView;

	}

	@PostMapping("/update")
	public ModelAndView update(User user, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		ModelAndView modelAndView = null;

		if (session != null) {
			userService.update(user);
			User oldUser = (User) session.getAttribute("user");
			modelAndView = new ModelAndView("welcome");
			User newUser = userService.getUser(oldUser.getUserName());
			session.setAttribute("user", newUser);

			if (((User) session.getAttribute("user")).getUserType().equals("admin")) {
				List<User> users = userService.getUsers();
				modelAndView.addObject("users", users);
			} else if (((User) session.getAttribute("user")).getUserType().equals("teacher")) {
				List<User> students = userService.getStudents();
				modelAndView.addObject("students", students);
			}

			modelAndView.addObject("updateSuccessMsg", "Profile is updated successfully");
		} else {
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("errorMessage", "Please Login First!!");
		}

		return modelAndView;
	}

	@GetMapping("/allow/{UserName}")
	public ModelAndView allow(@PathVariable String UserName, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		ModelAndView modelAndView = new ModelAndView("welcome");

		if (session != null) {
			String userStatus = "allow";
			userService.updateStatus(UserName, userStatus);
			List<User> users = userService.getUsers();
			modelAndView.addObject("users", users);
		}

		else {
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("errorMessage", "Please Login First!!");
		}
		return modelAndView;

	}

	@GetMapping("/block/{UserName}")
	public ModelAndView block(@PathVariable String UserName, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		ModelAndView modelAndView = new ModelAndView("welcome");

		if (session != null) {
			String userStatus = "block";
			userService.updateStatus(UserName, userStatus);
			List<User> users = userService.getUsers();
			modelAndView.addObject("users", users);
		}

		else {
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("errorMessage", "Please Login First!!");
		}
		return modelAndView;

	}
}
