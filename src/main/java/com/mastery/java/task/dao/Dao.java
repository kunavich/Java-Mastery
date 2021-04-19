package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;


import java.util.List;

public interface Dao {

    List<Employee> list();
    // CRUD
    Employee create(Employee employee);
    Employee get(Long id);
    Employee update(Long id, Employee employee);
    Employee delete(Long id);

}
