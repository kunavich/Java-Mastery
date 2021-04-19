package com.mastery.java.task.service;

import com.mastery.java.task.dao.Dao;
import com.mastery.java.task.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private Dao dao;

    @Override
    public List<Employee> getEmployees() {
        return dao.list();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return dao.create(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return dao.get(id);
    }

    @Override
    public Employee updateEmployeeById(Long id, Employee employee) {
        return dao.update(id, employee);
    }

    @Override
    public Employee deleteEmployeeById(Long id) {
        return dao.delete(id);
    }

}
