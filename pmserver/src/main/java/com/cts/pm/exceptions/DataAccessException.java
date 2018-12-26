package com.cts.pm.exceptions;

@SuppressWarnings("serial")
public class DataAccessException extends RuntimeException {

	public DataAccessException(Throwable e) {
	}
	
	public DataAccessException(String message) {
        super(message);
    }
}
