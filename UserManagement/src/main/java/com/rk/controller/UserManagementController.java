package com.rk.controller;

import javax.validation.Valid;

import com.rk.constants.EndPointsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.constants.ControllerConstants;
import com.rk.constants.ExceptionMessages;
import com.rk.repository.UserRepository;
import com.rk.request.SignUpForm;
import com.rk.response.ResponseMessage;
import com.rk.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = EndPointsConstants.USER_MANAGEMENT)
public class UserManagementController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	PasswordEncoder passwordEncoder;


	//private static final logger = LogManager.getLogger(UserManagementController.class);
			
			
	@PostMapping("/sign_up")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		System.out.println("inside register");
		if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.CONFLICT);
		}

		if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.CONFLICT);
		}
		
		if (Boolean.TRUE.equals(userRepository.existsBycontactno(signUpRequest.getContactNo()))) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Contact Number is already in use!"),
					HttpStatus.CONFLICT);
		}
		
		Boolean saveUser = userService.saveUser(signUpRequest);
		
		if(Boolean.FALSE.equals(saveUser)) {
			return new ResponseEntity<>(new ResponseMessage(ExceptionMessages.USER_NOT_SAVED),
					HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<>(new ResponseMessage(ControllerConstants.USER_REGISTER_SUCCESS), HttpStatus.OK);
	}

}
