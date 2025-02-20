package com.sample.test.service;

import java.util.List;

import com.sample.test.entity.Department;

public interface DepartmentService {
	
	Department saveDepartment(Department department);

	List<Department> fetchDepartmentList();

	Department updateDepartment(Department department, Long departmentId);

	void deleteDepartmentById(Long departmentId);
}
