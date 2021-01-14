package com.rk.util;

import javax.management.relation.RoleNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.rk.constants.UMSErrorCode;
import com.rk.response.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler {
	
	public ResponseEntity<Object> handleRoleNotFoundException(RoleNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse(UMSErrorCode.UMS001);
		return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
