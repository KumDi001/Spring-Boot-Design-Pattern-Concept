package com.sample.test.service;

import java.util.List;

import com.sample.test.entity.Employee;

public interface EmployeeService {
	Employee saveEmployee(Employee emp);

	List<Employee> fetchEmployeetList();

	Employee updateEmployee(Employee emp, Long empId);

	void deleteEmployeeById(Long empId);
}
