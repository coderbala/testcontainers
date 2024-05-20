package com.ds.demos.testcontainers;


import com.ds.demos.testcontainers.model.Employee;
import com.ds.demos.testcontainers.service.EmployeeService;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceIT {

    @Autowired
    private EmployeeService employeeService;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );

    @Test
    @Order(1)
    public void add() {

        Employee employee = new Employee();
        employee.setEmail("tester@testing.com");
        employee.setName("Tester testing");
        Employee newEmployee = employeeService.add(employee);
        Assertions.assertNotNull(newEmployee);
    }

    @Test
    @Order(2)
    public void findAll() {
        System.out.println("findAll started");

        List<Employee> employees = employeeService.findAll();
        Assertions.assertEquals(1, employees.size());
    }

}
