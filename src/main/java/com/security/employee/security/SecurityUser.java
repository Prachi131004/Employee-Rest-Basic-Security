package com.security.employee.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.security.employee.entity.Employee;


public class SecurityUser implements UserDetails{
	
	private static final long serialVersionUID = 1l;

	@Autowired
	private Employee employee;

	public SecurityUser(Employee employee) {
		super();
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.stream(employee.getRole().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	} 

	@Override
	public @Nullable String getPassword() {
		return employee.getPassword(); 
	}

	@Override
	public String getUsername() {
		return employee.getEmail();
	}

}
