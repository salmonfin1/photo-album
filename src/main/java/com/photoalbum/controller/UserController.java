package com.photoalbum.controller;

import com.photoalbum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {


	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}


}
