package com.sample.test.entity;

import com.sample.test.service.UniqueEmailValidator;
import com.sample.test.service.UniqueValidatorGroup;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
	@Id // Specifies the primary key of the entity.
	@GeneratedValue(strategy = GenerationType.AUTO) // Auto-generates the primary key value.
	private Long empId; // Unique identifier for the department.
	private String empName; // Name of the department.
	@jakarta.validation.constraints.Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@UniqueEmailValidator(message = "Email must be valid",groups= {UniqueValidatorGroup.class})
	private String emailAddress;
	private int age;
	private String phone_no;
	private String empAddress; // Address of the department.
	private int dept_id;
	@ManyToOne
	private Department department;

}
