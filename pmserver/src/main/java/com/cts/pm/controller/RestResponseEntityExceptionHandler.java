package com.cts.pm.controller;

import java.sql.SQLException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cts.pm.exceptions.DataAccessException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler 
  extends ResponseEntityExceptionHandler {
 
	 @ExceptionHandler(value = {SQLException.class, DataAccessException.class})
	 protected ResponseEntity<Object> handleSQLConflict(SQLException ex, WebRequest request) {
    	ex.printStackTrace();
		 System.out.println("SQL Exception");
        return handleExceptionInternal(ex, ex.getMessage(), 
          new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	 }
	 
    @ExceptionHandler(value = {  Exception.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    	ex.printStackTrace();
        return handleExceptionInternal(ex, ex.getMessage(), 
          new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    
    
}