package com.security.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.employee.entity.Employee;
import com.security.employee.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private PasswordEncoder encoder;

	public Employee registerUser(Employee employee) {
		if (employeeRepo.findByEmail(employee.getEmail()).isPresent()) {
			throw new RuntimeException("User already exist");
		}

		String hashPW = encoder.encode(employee.getPassword());
		employee.setPassword(hashPW);

		return employeeRepo.save(employee);
	}

	public Employee addEmployee(Employee emp) {
		if (employeeRepo.findByEmail(emp.getEmail()).isPresent()) {
			throw new RuntimeException("Employee already exists!");
		}
		emp.setPassword(encoder.encode(emp.getPassword())); 
		return employeeRepo.save(emp);
	}

	public List<Employee> getAll() {
		return employeeRepo.findAll();
	}

	public Employee getOne(Long id) {
		return employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
	}

	@Transactional
	public Employee updateEmployee(Long id, Employee updatedEmp) {
		return employeeRepo.findById(id).map(existingEmp -> {
			existingEmp.setEmail(updatedEmp.getEmail());
			existingEmp.setSalary(updatedEmp.getSalary());
			existingEmp.setDepartmentName(updatedEmp.getDepartmentName());
			existingEmp.setRole(updatedEmp.getRole());

			if (updatedEmp.getPassword() != null && !updatedEmp.getPassword().trim().isEmpty()) {
				existingEmp.setPassword(encoder.encode(updatedEmp.getPassword()));
			}

			return employeeRepo.save(existingEmp);
		}).orElseThrow(() -> new RuntimeException("Employee Not Found with id: " + id));
	}

	public void deleteEmployee(Long id) {
		employeeRepo.deleteById(id);
	}

}
