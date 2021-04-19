package com.mastery.java.task.rest;



import com.mastery.java.task.JsonUtil;
import com.mastery.java.task.config.AppConfiguration;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.service.EmployeeService;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, excludeAutoConfiguration = AppConfiguration.class)
public class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService service;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void whenPostEmployee_thenCreateEmployee() throws Exception {
        Employee alex = new Employee((long) 1,"alex", Gender.MALE);
        given(service.createEmployee(Mockito.any())).willReturn(alex);

        mvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(alex))).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("alex")));
        verify(service, VerificationModeFactory.times(1)).createEmployee(Mockito.any());
        reset(service);
    }

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {
        Employee alex = new Employee((long) 1,"alex", Gender.MALE);
        Employee john = new Employee((long) 2,"john",Gender.MALE);
        Employee bob = new Employee((long) 3,"bob",Gender.MALE);

        List<Employee> allEmployees = Arrays.asList(alex, john, bob);

        given(service.getEmployees()).willReturn(allEmployees);

        mvc.perform(get("/api/employees").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(alex.getFirstName())))
                .andExpect(jsonPath("$[1].name", is(john.getFirstName())))
                .andExpect(jsonPath("$[2].name", is(bob.getFirstName())));
        verify(service, VerificationModeFactory.times(1)).getEmployees();
        reset(service);
    }

}