package com.sample.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.test.entity.Department;
import com.sample.test.entity.Employee;
import com.sample.test.service.DepartmentService;
import com.sample.test.service.EmployeeService;
import com.sample.test.service.UniqueValidatorGroup;

@RestController
public class Controller {

	@Autowired
	private DepartmentService departmentService; // Injects the DepartmentService dependency.

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/addDepartments")
	public Department saveDepartment(@Validated @RequestBody Department department) {

		return departmentService.saveDepartment(department);
	}

	@GetMapping("/getDepartments")
	public List<Department> fetchDepartmentList() {
		return departmentService.fetchDepartmentList();
	}

	@PutMapping("/departments/{id}")
	public Department updateDepartment(@RequestBody Department department, @PathVariable("id") Long departmentId) {
		return departmentService.updateDepartment(department, departmentId);
	}

	@DeleteMapping("/departments/{id}")
	public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
		departmentService.deleteDepartmentById(departmentId);
		return "Deleted Successfully";
	}

	@PostMapping("/addEmployees")
	public Employee saveEmployee(@Validated(UniqueValidatorGroup.class) @RequestBody Employee employee) {

		return employeeService.saveEmployee(employee);
	}

	@GetMapping("/getEmployees")
	public List<Employee> fetchEmployeeList() {
		return employeeService.fetchEmployeetList();
	}

	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long employeeId) {
		return employeeService.updateEmployee(employee, employeeId);
	}

	@DeleteMapping("/employees/{id}")
	public String deleteEmployeeIdById(@PathVariable("id") Long employeeId) {
		employeeService.deleteEmployeeById(employeeId);
		return "Deleted Successfully";
	}
}
