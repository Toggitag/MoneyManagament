package com.rk.service;

import com.rk.entity.User;
import com.rk.request.SignUpForm;

public interface UserService {

	public Boolean saveUser(SignUpForm signUpRequest);

	public User getUser(String username);
}