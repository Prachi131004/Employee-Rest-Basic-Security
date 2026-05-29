package com.security.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.employee.repository.EmployeeRepository;
import com.security.employee.security.SecurityUser;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private EmployeeRepository employeeRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return employeeRepo.findByEmail(email).map(SecurityUser::new)
				.orElseThrow(() -> new RuntimeException("Employee Not found with email : " + email)); 
	}
	
	

}
