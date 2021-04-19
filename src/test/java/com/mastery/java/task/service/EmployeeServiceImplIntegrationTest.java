package com.mastery.java.task.service;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mastery.java.task.dao.Dao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplIntegrationTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private Dao dao;

    @Before
    public void setUp() {
        Employee john = new Employee( 1L,"john", Gender.MALE);
        Employee bob = new Employee( 2L,"bob", Gender.MALE);
        Employee alex = new Employee( 3L,"alex", Gender.FEMALE);

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);


        //Optional<Employee> employee= Optional.of(john);
        Mockito.when(dao.get(john.getId())).thenReturn(john);
        Mockito.when(dao.list()).thenReturn(allEmployees);
        Mockito.when(dao.get(-99L)).thenReturn(null);
    }

    @Test
    public void whenValidId_thenEmployeeShouldBeFound() {
        Employee fromDb = employeeService.getEmployeeById(1L);
        assertThat(fromDb.getFirstName()).isEqualTo("john");

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenInValidId_thenEmployeeShouldNotBeFound() {
        Employee fromDb = employeeService.getEmployeeById(-99L);
        verifyFindByIdIsCalledOnce();
        assertThat(fromDb).isNull();
    }

    @Test
    public void given3Employees_whengetAll_thenReturn3Records() {
        Employee john = new Employee( 1L,"john", Gender.MALE);
        Employee bob = new Employee( 2L,"bob", Gender.MALE);
        Employee alex = new Employee( 3L,"alex", Gender.FEMALE);

        List<Employee> allEmployees = employeeService.getEmployees();
        verifyFindAllEmployeesIsCalledOnce();
        assertThat(allEmployees).hasSize(3).extracting(Employee::getFirstName).contains(alex.getFirstName(), john.getFirstName(), bob.getFirstName());
    }


    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(dao, VerificationModeFactory.times(1)).get(Mockito.anyLong());
        Mockito.reset(dao);
    }

    private void verifyFindAllEmployeesIsCalledOnce() {
        Mockito.verify(dao, VerificationModeFactory.times(1)).list();
        Mockito.reset(dao);
    }
}