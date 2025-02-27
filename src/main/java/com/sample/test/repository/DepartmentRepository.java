package com.sample.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.test.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
