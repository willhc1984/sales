package com.sales.resources.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler{
	
//	@ExceptionHandler(NoSuchElementException.class)
//	public ResponseEntity<StandardError> noSuchElementException(NoSuchElementException e, HttpServletRequest request){
//		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
//	}

}
