package com.sample.test.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.test.entity.Employee;
import com.sample.test.repository.EmployeeRepository;
import com.sample.test.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return employeeRepository.save(emp);
	}

	@Override
	public List<Employee> fetchEmployeetList() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee updateEmployee(Employee emp, Long empId) {
		// TODO Auto-generated method stub
		Employee empDB = employeeRepository.findById(empId).get();

		// Updates fields if they are not null or empty.
		if (Objects.nonNull(empDB.getEmpName()) && !"".equalsIgnoreCase(empDB.getEmpName())) {
			empDB.setEmpName(emp.getEmpName());
		}
		if (Objects.nonNull(empDB.getEmpAddress()) && !"".equalsIgnoreCase(empDB.getEmpAddress())) {
			empDB.setEmpAddress(emp.getEmpAddress());
		}
		// Saves and returns the updated department entity.
		return employeeRepository.save(empDB);
	}

	@Override
	public void deleteEmployeeById(Long empId) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(empId);

	}

}
