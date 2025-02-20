package com.sample.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.test.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByEmailAddress(String emailAddress);

}
