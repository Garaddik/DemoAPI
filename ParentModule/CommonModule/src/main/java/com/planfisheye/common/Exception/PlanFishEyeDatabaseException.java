package com.planfisheye.common.Exception;

public class PlanFishEyeDatabaseException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private int status;

	public String getMessage() {
		return message;
	}

	public PlanFishEyeDatabaseException(int status,String message) {
		super(message);
		this.message = message;
		this.status = status;
	}

	public int getStatus() {
		return status;
	}
}
