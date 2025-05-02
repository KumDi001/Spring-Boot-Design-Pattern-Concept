package com.sample.test.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
import com.sample.test.exceptions.DepartmentAlreadyExistsException;
import com.sample.test.exceptions.DepartmentNotFoundException;
import com.sample.test.exceptions.EmployeeAlreadyExistsException;
import com.sample.test.exceptions.EmployeeNotFoundException;
import com.sample.test.service.DepartmentService;
import com.sample.test.service.EmployeeService;
import com.sample.test.service.UniqueValidatorGroup;

@RestController
public class EmployeeController {

	@Autowired
	private DepartmentService departmentService; // Injects the DepartmentService dependency.

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/addDepartments")
	public Department saveDepartmentWithEmp(@Validated @RequestBody Department department)
			throws DepartmentAlreadyExistsException, EmployeeNotFoundException, EmployeeAlreadyExistsException {

		return departmentService.saveDepartment(department, 1);
	}

	@PostMapping("/addDepartment")
	public Department saveDepartment(@Validated @RequestBody Department department)
			throws DepartmentAlreadyExistsException, EmployeeNotFoundException, EmployeeAlreadyExistsException {

		return departmentService.saveDepartmentFisrtTime(department);
	}

	@GetMapping("/getDepartments")
	public List<Department> fetchDepartmentList() throws DepartmentNotFoundException {
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
	public ResponseEntity<?> saveEmployee(@Validated(UniqueValidatorGroup.class) @RequestBody Employee employee)
			throws EmployeeAlreadyExistsException {
		try {
			Employee emp = employeeService.saveEmployee(employee);
			return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/getEmployees")
	public ResponseEntity<List<?>> fetchEmployeeList() {
		try {
			List<Employee> emp = employeeService.fetchEmployeetList();
			return new ResponseEntity<>(emp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getEmployees/{id}")
	public ResponseEntity<?> fetchEmployeeByID(@PathVariable("id") Long employeeId) throws EmployeeNotFoundException {
		Optional<Employee> emp = Optional.ofNullable(employeeService.getEmployeeById(employeeId));
		return emp.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).get();
		/*
		 * if (emp.isPresent()) { return new ResponseEntity(emp.get(), HttpStatus.OK); }
		 * return new ResponseEntity(HttpStatus.NOT_FOUND);
		 */
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,
			@PathVariable("id") Long employeeId) {
		try {
			Employee emp = employeeService.updateEmployee(employee, employeeId);
			return new ResponseEntity<>(emp, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Object> deleteEmployeeIdById(@PathVariable("id") Long employeeId)
			throws EmployeeNotFoundException {
		Optional<Employee> emp = Optional.ofNullable(employeeService.getEmployeeById(employeeId));
		emp.map(entry -> new ResponseEntity<>(entry, HttpStatus.FOUND)).get();
		try {
			employeeService.deleteEmployeeById(employeeId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}

	}

}