package com.javaAssignment.EmployeeManagement.test;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.javaAssignment.EmployeeManagement.model.Employee;
import com.javaAssignment.EmployeeManagement.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {

	@Autowired
	EmployeeService employeeService;
	
	@Test
	public void testAddEmployee() {
		
		/*
		Employee manager = new Employee();
		manager.setFirstName("Chetan");
		manager.setLastName("amale");
		manager.setEmail("chetan@gmail.com");		
		
		
		Employee emp  = new Employee();
		emp.setFirstName("Neil");
		emp.setLastName("Amale");
		emp.setManager(manager);
		
		employeeService.save(emp);	
		*/
		
		try {
		/*Employee emp  = new Employee();
		emp.setFirstName("Neha");
		emp.setLastName("Amale");
		emp.setManager(employeeService.findOneByEmployeeId(1));
		
		System.out.println("Employee Manager name:" + emp.getManager().getEmployeeId());
		System.out.println("Employee Manager name:" + emp.getFirstName());
		
		employeeService.save(emp);	
		
		Employee dbEmployee = employeeService.findOneByEmployeeId(emp.getEmployeeId());
		System.out.println("Employee Manager name:" + dbEmployee.getEmployeeId());
		assertNotNull(dbEmployee);
			
	    Set<Employee> employeeList = employeeService.findAllByManager(employeeService.findOneByEmployeeId(1));
	     
		*/}
		catch(Exception e) {
			System.out.println("Exception is:" + e.getMessage());
		}
		
	}

}
