package com.sample.test.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sample.test.repository.EmployeeRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidation implements ConstraintValidator<UniqueEmailValidator, String> {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (value == null) {
			return true;
		}

		// Check if email already exists
		var existingEmployee = employeeRepository.findByEmailAddress(value);
		return existingEmployee == null; // true means it's unique

	}

}
