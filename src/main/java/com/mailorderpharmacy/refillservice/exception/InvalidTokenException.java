/**
 * 
 */
package com.mailorderpharmacy.refillservice.exception;

@SuppressWarnings("serial")
public class InvalidTokenException extends Exception {
	
	public InvalidTokenException(String msg) {
		super(msg);
	}
}
