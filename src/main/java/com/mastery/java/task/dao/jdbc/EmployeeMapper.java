package com.mastery.java.task.dao.jdbc;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId((long) rs.getInt("id"));
        employee.setFirstName(rs.getString("firstname"));
        //employee.setGender(rs.getString("gender"));
        employee.setGender(Gender.valueOf(rs.getString("gender")));
        return employee;
    }
}