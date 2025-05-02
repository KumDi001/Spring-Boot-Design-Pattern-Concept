package com.sample.test.service;

import java.util.List;

import com.sample.test.entity.Department;
import com.sample.test.exceptions.EmployeeAlreadyExistsException;
import com.sample.test.exceptions.EmployeeNotFoundException;

public interface DepartmentService {
	
	Department saveDepartment(Department department, long emp_id) throws EmployeeNotFoundException, EmployeeAlreadyExistsException;

	List<Department> fetchDepartmentList();

	Department updateDepartment(Department department, Long departmentId);

	void deleteDepartmentById(Long departmentId);

	Department saveDepartmentFisrtTime(Department department);
}
