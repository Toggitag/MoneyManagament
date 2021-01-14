package com.rk.service;

import com.rk.request.SignUpForm;

public interface UserService {

	public Boolean saveUser(SignUpForm signUpRequest);
}