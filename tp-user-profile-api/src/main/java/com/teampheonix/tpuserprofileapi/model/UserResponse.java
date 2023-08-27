package com.teampheonix.tpuserprofileapi.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class UserResponse {

	@Column(unique=true)
	private String userId;
	private String email;
	private String password;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
	//private String Role;
	private Set<Role> role = new HashSet<>();

}
