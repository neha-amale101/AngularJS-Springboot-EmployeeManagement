package com.javaAssignment.EmployeeManagement.controller;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javaAssignment.EmployeeManagement.model.Employee;
import com.javaAssignment.EmployeeManagement.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeResource {

	@Autowired	
	EmployeeService employeeService;
	
	/* Save Manager Signup*/	
	@RequestMapping(value = "/manager/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> managerSignup(@Valid @RequestBody Employee emp) throws URISyntaxException 
	{		
		try {

			Employee result = employeeService.save(emp);
			return ResponseEntity.created(new URI("/api/manager/employee/" + result.getEmployeeId())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity<Employee>(HttpStatus.CONFLICT);
		}	
	}
	
	/* Save Employee details for given manager*/	
	@RequestMapping(value = "/manager/{managerId}/addEmployee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> addEmployee(@PathVariable(value = "managerId") Integer managerId,@Valid @RequestBody Employee emp) throws URISyntaxException 
	{		
		try {

			Employee manager = employeeService.findOneByEmployeeId(managerId);
			emp.setManager(manager);
			Employee result = employeeService.save(emp);
			return ResponseEntity.created(new URI("/api/manager/"+managerId+"addEmployee/" + result.getEmployeeId())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity<Employee>(HttpStatus.CONFLICT);
		}	
	}
	
	/* Update an employee*/
	@RequestMapping(value = "/manager/{managerId}/updateEmployee", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="managerId") Integer managerId,@RequestBody Employee empDetails) throws URISyntaxException{
		
		if (empDetails.getEmployeeId() == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		try {

			Employee manager = employeeService.findOneByEmployeeId(managerId);
			empDetails.setManager(manager);			
			Employee result = employeeService.update(empDetails);

			return ResponseEntity.created(new URI("/api/manager/"+managerId+"updateEmployee/" + result.getEmployeeId())).body(result);

		} catch (EntityNotFoundException e) {

			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);

		}

	}
	
	/*Delete an Employee*/
	@RequestMapping(value = "/manager/{managerId}/deleteEmployee", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> deleteEmployee(@RequestBody Employee emp){
		 
		if (emp == null || emp.getEmployeeId() == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		 }
		 Employee empDetails =  employeeService.findOneByEmployeeId(emp.getEmployeeId());		 
		 if(empDetails == null)
		 {
			 return ResponseEntity.notFound().build();
		 }		 
		 employeeService.delete(emp);
		 return ResponseEntity.ok().build();
	}	
	
	/*Get one Employee based on Employee Id*/
	@RequestMapping(value = "/manager/{managerId}/getEmployee/{employeeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value ="managerId") Integer managerId,@PathVariable(value ="employeeId") Integer employeeId){
		 
		Employee emp =  employeeService.findOneByEmployeeId(employeeId);		 
		 if(emp == null)
		 {
			 return ResponseEntity.notFound().build();
		 }		 
		 return ResponseEntity.ok().body(emp);
	}	
	
	/*Get all Employee based on Manager Id*/
	@RequestMapping(value = "/manager/{managerId}/getAllEmployee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Employee>> getEmployeeById(@PathVariable(value ="managerId") Integer managerId){
		 
		Employee manager = employeeService.findOneByEmployeeId(managerId);
		 if(manager == null)
		 {
			 return ResponseEntity.notFound().build();
		 }		 
		
		 Set<Employee> empList =  employeeService.findAllByManager(manager);		 
		 if(empList == null || empList.size() == 0)
		 {
			 return ResponseEntity.notFound().build();
		 }
		 
		 return ResponseEntity.ok().body(empList);
	}
}
