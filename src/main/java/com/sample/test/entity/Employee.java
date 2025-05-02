package com.sample.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sample.test.service.UniqueEmailValidator;
import com.sample.test.service.UniqueValidatorGroup;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Employee {
	@Id // Specifies the primary key of the entity.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the primary key value.
	private Long empId; // Unique identifier for the department.
	private String empName; // Name of the department.
	private String password;
	@jakarta.validation.constraints.Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@UniqueEmailValidator(message = "Email must be valid", groups = { UniqueValidatorGroup.class })
	private String emailAddress;
	private int age;
	private int salary;
	private String phone_no;
	private String empAddress; // Address of the department

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "departmentId", nullable = false)
	@JsonIgnore
	private Department department;

	private Employee(EmployeeBuilder builder) {
		this.empId = builder.empId;
		this.empName = builder.empName;
		this.password = builder.password;
		this.age = builder.age;
		this.emailAddress = builder.emailAddress;
		this.phone_no = builder.phone_no;
		this.empAddress = builder.empAddress;
		this.salary = builder.salary;
	}

	public static class EmployeeBuilder {

		private Long empId;
		private String empName; // Name of the department.
		private String password;
		private String emailAddress;
		private int age;
		private int salary;
		private String phone_no;
		private String empAddress; // Address of the department.
		private Department department;

		public EmployeeBuilder setPassword(String password) {
			this.password = password;
			return this;
		}

		public EmployeeBuilder setEmpName(String empName) {
			this.empName = empName;
			return this;
		}

		public EmployeeBuilder setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
			return this;
		}

		public EmployeeBuilder setAge(int age) {
			this.age = age;
			return this;
		}

		public EmployeeBuilder setPhone_no(String phone_no) {
			this.phone_no = phone_no;
			return this;
		}

		public EmployeeBuilder setEmpAddress(String empAddress) {
			this.empAddress = empAddress;
			return this;
		}

		public EmployeeBuilder setSalary(int salary) {
			this.salary = salary;
			return this;
		}

		public EmployeeBuilder setDepartment(Department department) {
			this.department = department;
			return this;
		}

		public Employee build() {
			return new Employee(this);
		}

	}

}
