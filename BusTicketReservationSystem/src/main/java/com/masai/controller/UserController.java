package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.UserException;
import com.masai.model.User;
import com.masai.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService uService;

	@PostMapping("/add")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) throws UserException {
		User u=uService.addUser(user);
		return new ResponseEntity<User>(u,HttpStatus.CREATED);
	}
	
	@PostMapping("/update")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user,@RequestParam String key) throws UserException {
		User u=uService.updateUser(user,key);
		return new ResponseEntity<User>(u,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("delete/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable("userId") Integer userId) throws UserException {
		User u=uService.deleteUser(userId);
		return new ResponseEntity<User>(u,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/view/{userId}")
	public ResponseEntity<User> viewUser(@PathVariable("userId") Integer userId) throws UserException {
		User u=uService.viewUser(userId);
		return new ResponseEntity<User>(u,HttpStatus.FOUND);
	}
	
	@GetMapping("/viewall")
	public ResponseEntity<List<User>> viewAllUsers() throws UserException {
		List<User> users=uService.viewAllUsers();
		return new ResponseEntity<List<User>>(users,HttpStatus.FOUND);
	}
}