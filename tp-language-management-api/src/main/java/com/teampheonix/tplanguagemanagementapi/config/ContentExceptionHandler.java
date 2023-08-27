package com.teampheonix.tplanguagemanagementapi.config;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.teampheonix.tplanguagemanagementapi.entity.ErrorDto;
import com.teampheonix.tplanguagemanagementapi.entity.ResponseDto;
import com.teampheonix.tplanguagemanagementapi.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ContentExceptionHandler {
	
	@ExceptionHandler(value=ResourceNotFoundException.class)
	public ResponseEntity<ResponseDto<Object>> ContentNotFoundException(ResourceNotFoundException ex,HttpServletRequest req){
//		ErrorDto error=new ErrorDto("3001", ex.getMessage());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseDto.forError(Arrays.asList(error)));
		  ErrorDto error = new ErrorDto(ex.getErrorCode(), ex.getErrorMessage());
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.forError(Arrays.asList(error)));
	}

}
