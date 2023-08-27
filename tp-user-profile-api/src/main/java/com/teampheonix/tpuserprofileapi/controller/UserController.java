package com.teampheonix.tpuserprofileapi.controller;

import com.teampheonix.tpuserprofileapi.model.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teampheonix.tpuserprofileapi.service.UserService;

@RestController
@RequestMapping("/api/user-profile/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/register")
	public ResponseEntity<ResponseDto<String>> register(@RequestBody UserRequest userRequest) {
		service.registerUser(userRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.forSuccess("User Register Successfully"));
	}

	@GetMapping("/details")
	public ResponseEntity<ResponseDto<UserResponse>> getUserDetails(HttpServletRequest request) {
		String userId = request.getHeader("USER_ID");
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.forSuccess(service.getUser(userId)));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<ResponseDto<UserResponse>> getUser(@PathVariable String userId) {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.forSuccess(service.getUser(userId)));
	}

	@PutMapping("/{userId}")
	public ResponseEntity<ResponseDto<User>> updateUSer(@PathVariable String userId, @RequestBody User user) {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.forSuccess(service.updateUser(user, userId)));
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ResponseDto<String>> deleteUser(@PathVariable String userId) {
		service.deleteUser(userId);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.forSuccess("User deleted successfully!!"));
	}
		
	@PostMapping("/validate")
    public ResponseEntity<ResponseDto<UserClaimsResponse>> login(@RequestBody UserAuthRequest userRequest) {
        UserClaimsResponse user = service.login(userRequest);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.forSuccess(user));
    }
	
}
