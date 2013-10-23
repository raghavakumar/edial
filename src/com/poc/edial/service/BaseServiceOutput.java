package com.poc.edial.service;

import java.util.List;

public abstract class BaseServiceOutput implements ServiceOutput {
	private List<ServiceError> errors;

	public List<ServiceError> getErrors() {
		return errors;
	}

	public void setErrors(List<ServiceError> errors) {
		this.errors = errors;
	}
	
}
