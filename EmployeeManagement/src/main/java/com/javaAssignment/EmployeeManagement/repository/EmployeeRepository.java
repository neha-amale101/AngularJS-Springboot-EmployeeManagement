package com.javaAssignment.EmployeeManagement.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaAssignment.EmployeeManagement.model.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{
	
	Set<Employee> findAllByManager(Employee manager);
}
