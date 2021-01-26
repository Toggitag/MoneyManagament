package com.rk.controller;

import javax.validation.Valid;

import com.rk.constants.EndPointsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.constants.ExceptionMessages;
import com.rk.repository.RoleRepository;
import com.rk.request.RoleRequest;
import com.rk.response.ResponseMessage;
import com.rk.service.RoleService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(EndPointsConstants.ROLE_MANAGEMENT)
public class RoleManagementController {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleService roleService;
	
	@PostMapping("/save_role")
	public ResponseEntity<Object> saveRole(@Valid @RequestBody RoleRequest roleRequest){
		
		
		if (Boolean.TRUE.equals(roleRepository.existsByRoleName(roleRequest.getRoleName()))) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Rolename is already taken!"),
					HttpStatus.BAD_REQUEST);
		}
		Boolean saveRole = roleService.saveRole(roleRequest);
		
		if(Boolean.FALSE.equals(saveRole)) {
			return new ResponseEntity<>(new ResponseMessage(ExceptionMessages.ROLE_NAME_NOT_SAVED),
					HttpStatus.NOT_IMPLEMENTED);
		}
		
		return new ResponseEntity<>(new ResponseMessage(ExceptionMessages.ROLE_NAME_SAVED),
				HttpStatus.CREATED);
	}
}
