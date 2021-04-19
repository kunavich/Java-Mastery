package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;

import java.util.List;

public interface EmployeeService {



    List<Employee> getEmployees();
    Employee createEmployee(Employee consultant);
    Employee getEmployeeById(Long id);
    Employee updateEmployeeById(Long id, Employee consultant);
    Employee deleteEmployeeById(Long id);
}
