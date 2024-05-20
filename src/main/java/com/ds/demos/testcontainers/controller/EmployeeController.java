package com.ds.demos.testcontainers.controller;

import com.ds.demos.testcontainers.model.Employee;
import com.ds.demos.testcontainers.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.add(employee));
    }
}
