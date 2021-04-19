package com.mastery.java.task.dao.jdbc;


import com.mastery.java.task.dao.Dao;
import com.mastery.java.task.dto.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JdbcEmployeeDao implements Dao {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;



    public JdbcEmployeeDao( DataSource dataSource)
    {
        this.dataSource=dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Employee> list() {
        String SQL = "SELECT * FROM employee";
        return (List) jdbcTemplate.query(SQL, new EmployeeMapper());

    }

    @Override
    public Employee create(Employee employee) {
        String SQL = "INSERT INTO employee (firstname, gender) VALUES (?,?)";

        //TODO update and execute
        jdbcTemplate.update(SQL, employee.getFirstName(), employee.getGender().toString());
        return employee;

    }

    @Override
    public Employee get(Long id) {
        String SQL = "SELECT * FROM employee WHERE id = ?";
        return (Employee) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new EmployeeMapper());
    }

    @Override
    public Employee update(Long id, Employee employee) {
        String SQL = "UPDATE employee SET firstname = ?, gender = ? WHERE id = ?";
        jdbcTemplate.update(SQL, employee.getFirstName(), employee.getGender().toString(), id);
        return employee;

    }

    @Override
    public Employee delete(Long id) {
        Employee employee = get(id);
        String SQL = "DELETE FROM employee WHERE id = ?";
        jdbcTemplate.update(SQL, id);
        return employee;
    }
}
