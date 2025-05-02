package com.sample.test.service;

import java.util.List;

import com.sample.test.entity.Employee;
import com.sample.test.exceptions.EmployeeAlreadyExistsException;
import com.sample.test.exceptions.EmployeeNotFoundException;

public interface EmployeeService {
	Employee saveEmployee(Employee emp) throws EmployeeAlreadyExistsException;

	List<Employee> fetchEmployeetList();

	Employee updateEmployee(Employee emp, Long empId);

	void deleteEmployeeById(Long empId);

	Employee getEmployeeById(Long empId) throws EmployeeNotFoundException;
}
