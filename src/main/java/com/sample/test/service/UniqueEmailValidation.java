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
		if (employeeRepository.findByEmailAddress(value).size() == 0)
			return true;
		return false;
	}

}
