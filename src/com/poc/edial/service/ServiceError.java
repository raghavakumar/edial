package com.poc.edial.service;

import android.database.SQLException;

public class ServiceError {
	public static final int DB_ERR = 1001;
	public static final int SERVICE_ERR = 1001;
	public static final int INVALID_INPUT_ERR = 1002;
	public static final int UNDEF_ERR = -1;
	
	int code;
	String description;

	public ServiceError(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public ServiceError(Exception e) {
		this.description = e.getMessage();
		this.code =  lookUpErrorCode(e);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceError [code=");
		builder.append(code);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	private int lookUpErrorCode(Exception e) {
		//TODO: resolved the error-code from a property-file/resource  code mapped to Exception Type.
		int code = 0;
		if (e instanceof SQLException) {
			code = DB_ERR;
		} else {
			code = UNDEF_ERR;
		}
		return code;
	}

}
