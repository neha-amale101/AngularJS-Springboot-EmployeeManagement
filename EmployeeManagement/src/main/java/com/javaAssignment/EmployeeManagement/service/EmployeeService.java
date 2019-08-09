package com.javaAssignment.EmployeeManagement.service;

import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaAssignment.EmployeeManagement.model.Employee;
import com.javaAssignment.EmployeeManagement.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	/* Save an employee*/	
	public Employee save(Employee emp) {
		
		if (emp.getEmployeeId() != null && employeeRepository.existsById(emp.getEmployeeId())) {
			throw new EntityExistsException("There is already existing employee with such ID in the database.");
		}
		return employeeRepository.save(emp);
	}
	
	/* To update Employee Details*/
	public Employee update(Employee employee) {

		if (employee.getEmployeeId() != null && !employeeRepository.existsById(employee.getEmployeeId())) {

			throw new EntityNotFoundException("There is no employee with such ID in the database.");
		}
		return employeeRepository.save(employee);
	}	
	
	/*Delete an employee*/
	public void delete(Employee emp) {		
		employeeRepository.delete(emp);
	}
	
	/*Find one employee by EmployeeId*/
	public Employee findOneByEmployeeId(Integer employeeId) {
		Employee emp = null;
		Optional<Employee> optEmp = employeeRepository.findById(employeeId);
		if(optEmp.isPresent())
			emp = optEmp.get();
		return emp;
	}
	
	/* Find all employee of manager*/
	public Set<Employee> findAllByManager(Employee manager){
		
		return employeeRepository.findAllByManager(manager);
	}

	
}
