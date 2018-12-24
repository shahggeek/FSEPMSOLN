package com.cts.pm.exceptions;

public class DataAccessException extends Exception {

	public DataAccessException(Throwable e) {
	}
	
	public DataAccessException(String message) {
        super(message);
    }
}
