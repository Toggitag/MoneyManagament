package com.rk.serviceimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rk.entity.Role;
import com.rk.entity.User;
import com.rk.repository.RoleRepository;
import com.rk.repository.UserRepository;
import com.rk.request.SignUpForm;
import com.rk.service.UserService;

@Service
public class UserManagementServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Boolean saveUser(SignUpForm signUpRequest) {

		User user = new User();

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setEmail(signUpRequest.getEmail());
		user.setContactno(signUpRequest.getContactNo());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		List<Role> rolesList = roleRepository.findAll();
		strRoles.forEach(role -> {
			rolesList.forEach(roleFromDb -> {
				if (role.equals(roleFromDb.getRoleName()))
					;
				roles.add(roleFromDb);
			});
		});

		user.setRoles(roles);
		userRepository.save(user);

		return true;

	}

	@Override
	public User getUser(String username) {
		User findByUsername = userRepository.findByUsername(username);
		return findByUsername;
	}
}
