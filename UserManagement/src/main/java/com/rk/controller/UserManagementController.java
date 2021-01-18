package com.rk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.constants.ControllerConstants;
import com.rk.constants.ExceptionMessages;
import com.rk.entity.User;
import com.rk.repository.UserRepository;
import com.rk.request.LoginForm;
import com.rk.request.SignUpForm;
import com.rk.response.JwtResponse;
import com.rk.response.ResponseMessage;
import com.rk.security.JwtProvider;
import com.rk.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/user_management")
public class UserManagementController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	// private static final logger =
	// LogManager.getLogger(UserManagementController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/generate_token")
	public ResponseEntity authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}

	@PostMapping("/sign_up")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		System.out.println("inside register");
		if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		if (Boolean.TRUE.equals(userRepository.existsBycontactno(signUpRequest.getContactNo()))) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		Boolean saveUser = userService.saveUser(signUpRequest);

		if (Boolean.FALSE.equals(saveUser)) {
			return new ResponseEntity<>(new ResponseMessage(ExceptionMessages.USER_NOT_SAVED),
					HttpStatus.NOT_IMPLEMENTED);
		}

		return new ResponseEntity<>(new ResponseMessage(ControllerConstants.USER_REGISTER_SUCCESS), HttpStatus.OK);
	}

	@GetMapping("/get_user/{username}")
	public ResponseEntity<?> getUser(@PathVariable String username) {

		User user = userService.getUser(username);

		if (user == null) {
			return new ResponseEntity<>(new ResponseMessage(ExceptionMessages.USER_NOT_SAVED),
					HttpStatus.NOT_IMPLEMENTED);
		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
