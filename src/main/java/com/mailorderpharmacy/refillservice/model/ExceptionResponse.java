package com.mailorderpharmacy.refillservice.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
	
	String messge;
	LocalDateTime timestamp;
	HttpStatus status;
	
	public ExceptionResponse() {
	}
	public ExceptionResponse(String messge, LocalDateTime timestamp, HttpStatus status) {
		this.messge = messge;
		this.timestamp = timestamp;
		this.status = status;
	}
	
	public String getMessge() {
		return messge;
	}
	public void setMessge(String messge) {
		this.messge = messge;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
}
