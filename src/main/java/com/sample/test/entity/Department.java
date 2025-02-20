package com.sample.test.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

	@Id // Specifies the primary key of the entity.
	@GeneratedValue(strategy = GenerationType.AUTO) // Auto-generates the primary key value.
	private Long departmentId; // Unique identifier for the department.
	private String departmentName; // Name of the department.
	private String departmentAddress; // Address of the department.
	@JsonIgnore
	  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "department")
	  private List<Employee> employee;

}
