package com.springbank.user.cmd.api.errorhandler;

import java.util.Date;

public class ExceptionResponse {
	
	private Date timeStamp;
	private String message;
	private String Details;
	public ExceptionResponse(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		Details = details;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return Details;
	}

}
