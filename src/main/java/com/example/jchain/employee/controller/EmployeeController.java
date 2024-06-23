package com.example.jchain.employee.controller;

import com.example.jchain.controller.BaseController;
import com.example.jchain.employee.model.Employee;
import com.example.jchain.employee.model.request.EmployeeRequest;
import com.example.jchain.employee.model.request.LoginRequest;
import com.example.jchain.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController extends BaseController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.createEmployee(employeeRequest);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Employee> login(@RequestBody LoginRequest loginRequest) {
        Employee employee = employeeService.getEmployeeByKey(loginRequest.getKey());
        if (employee != null && employee.getEmail().equals(loginRequest.getEmail())) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
}
