package com.teampheonix.tptopicmanagementapi.model;

import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {

	private String code;
	private String message;

}
