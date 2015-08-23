package com.loiane.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.loiane.dao.EmployeeDao;
import com.loiane.model.Employee;

public class EmployeeServiceImplTest {

	@Mock
	EmployeeDao dao;
	
	@InjectMocks
	EmployeeServiceImpl employeeService;
	
	@Spy
	List<Employee> employees = new ArrayList<Employee>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		employees = getEmployeeList();
	}

	@Test
	public void findById(){
		Employee emp = employees.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		Assert.assertEquals(employeeService.findById(emp.getId()),emp);
	}

	@Test
	public void saveEmployee(){
		doNothing().when(dao).saveEmployee(any(Employee.class));
		employeeService.saveEmployee(any(Employee.class));
		verify(dao, atLeastOnce()).saveEmployee(any(Employee.class));
	}
	
	@Test
	public void updateEmployee(){
		Employee emp = employees.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		employeeService.updateEmployee(emp);
		verify(dao, atLeastOnce()).findById(anyInt());
	}

	@Test
	public void deleteEmployeeBySsn(){
		doNothing().when(dao).deleteEmployeeBySsn(anyString());
		employeeService.deleteEmployeeBySsn(anyString());
		verify(dao, atLeastOnce()).deleteEmployeeBySsn(anyString());
	}
	
	@Test
	public void findAllEmployees(){
		when(dao.findAllEmployees()).thenReturn(employees);
		Assert.assertEquals(employeeService.findAllEmployees(), employees);
	}
	
	@Test
	public void findEmployeeBySsn(){
		Employee emp = employees.get(0);
		when(dao.findEmployeeBySsn(anyString())).thenReturn(emp);
		Assert.assertEquals(employeeService.findEmployeeBySsn(anyString()), emp);
	}

	@Test
	public void isEmployeeSsnUnique(){
		Employee emp = employees.get(0);
		when(dao.findEmployeeBySsn(anyString())).thenReturn(emp);
		Assert.assertEquals(employeeService.isEmployeeSsnUnique(emp.getId(), emp.getSsn()), true);
	}
	
	
	public List<Employee> getEmployeeList(){
		Employee e1 = new Employee();
		e1.setId(1);
		e1.setName("Axel");
		e1.setJoiningDate(new LocalDate());
		e1.setSalary(new BigDecimal(10000));
		e1.setSsn("XXX111");
		
		Employee e2 = new Employee();
		e2.setId(2);
		e2.setName("Jeremy");
		e2.setJoiningDate(new LocalDate());
		e2.setSalary(new BigDecimal(20000));
		e2.setSsn("XXX222");
		
		employees.add(e1);
		employees.add(e2);
		return employees;
	}
	
}
