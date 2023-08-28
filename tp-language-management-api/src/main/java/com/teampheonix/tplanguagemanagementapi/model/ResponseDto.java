package com.teampheonix.tplanguagemanagementapi.model;

import java.util.List;

public class ResponseDto<T> {
	
	 private String status;
	 private T data;
	 private List<ErrorDto> errors;
	 	 
	 public ResponseDto(String status, T data, List<ErrorDto> errors) {
		super();
		this.status = status;
		this.data = data;
		this.errors = errors;
	}
	 
	 

	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public T getData() {
		return data;
	}



	public void setData(T data) {
		this.data = data;
	}



	public List<ErrorDto> getErrors() {
		return errors;
	}



	public void setErrors(List<ErrorDto> errors) {
		this.errors = errors;
	}



	public static <T> ResponseDto<T> forSuccess(T data) {
		 return new ResponseDto<>("SUCCESS", data, null);
	 }

	 public static ResponseDto<Object> forError(List<ErrorDto> errors) {
	    return new ResponseDto<>("ERROR", null, errors);
	 }

}
