package com.mastery.java.task.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mastery.java.task.dto.Employee;


import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Api(value = "Employee Controller", description = "REST APIs for Employees")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "Get list of Employees in the System ", response = Iterable.class, tags = "Get all Employees")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployees();
    }

    /**
     * Gets Employee by id.
     *
     * @param employeeId the Employee id
     * @return the Employee by id
     * @throws EmployeeNotFoundException the resource not found exception
     */
    @ApiOperation(value = "Get specific Employee in the System ", response = Employee.class, tags = "Get Employee")
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws EmployeeNotFoundException {
        Employee employee = employeeService.getEmployeeById(employeeId);
                      //  .orElseThrow(() -> new EmployeeNotFoundException("Employee not found on :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    /**
     * Create employee.
     *
     * @param employee the employee
     * @return the employee
     */
    @ApiOperation(value = "Сreate specific Employee in the System ", response = Employee.class, tags = "Сreate Employee")
    @PostMapping("/employee")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    /**
     * Update Employee response entity.
     *
     * @param employeeId the Employee id
     * @param employeeDetails the Employee details
     * @return the response entity
     * @throws EmployeeNotFoundException the resource not found exception
     */
    @ApiOperation(value = "Update specific Employee in the System ", response = Employee.class, tags = "Update Employee")
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable(value = "id") Long employeeId, @Valid @RequestBody Employee employeeDetails)
            throws EmployeeNotFoundException {

        final Employee updatedEmployee = employeeService.updateEmployeeById(employeeId,employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    /**
     * Delete Employee map.
     *
     * @param employeeId the Employee id
     * @return the map
     */
    @ApiOperation(value = "Delite specific Employee in the System ", response = Employee.class, tags = "Delite Employee")
    @DeleteMapping("/employee/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)  {

        employeeService.deleteEmployeeById(employeeId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}