package com.sample.test.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sample.test.entity.Employee;
import com.sample.test.exceptions.EmployeeAlreadyExistsException;
import com.sample.test.exceptions.EmployeeNotFoundException;
import com.sample.test.repository.EmployeeRepository;
import com.sample.test.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeRepository.findByEmailAddress(username);
		if (employee != null) {
			return org.springframework.security.core.userdetails.User.builder().username(employee.getEmailAddress())
					.password(employee.getPassword()).build();
		}
		throw new UsernameNotFoundException("Employee not found with username: " + username);
	}

	@Override
	public Employee saveEmployee(Employee emp) throws EmployeeAlreadyExistsException {
		// TODO Auto-generated method stub
		/*
		 * Employee existingEmployee =
		 * employeeRepository.findById(emp.getEmpId()).orElse(null); if
		 * (existingEmployee != null) { throw new
		 * EmployeeAlreadyExistsException("Employee already exists with the given ID.");
		 * }
		 */
		emp.setPassword(encoder.encode(emp.getPassword()));
		return employeeRepository.save(emp);
	}

	@Override
	public List<Employee> fetchEmployeetList() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Long empId) throws EmployeeNotFoundException {
		// TODO Auto-generated method stub
		return employeeRepository.findById(empId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with the given ID."));

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
