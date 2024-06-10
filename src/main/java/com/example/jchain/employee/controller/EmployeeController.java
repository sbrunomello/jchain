package com.example.jchain.employee.controller;

import com.example.jchain.attendance.service.AttendanceService;
import com.example.jchain.blockchain.model.Block;
import com.example.jchain.blockchain.model.Transaction;
import com.example.jchain.blockchain.service.BlockchainService;
import com.example.jchain.employee.model.Employee;
import com.example.jchain.employee.model.request.EmployeeRequest;
import com.example.jchain.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.createEmployee(employeeRequest);
        return ResponseEntity.ok(employee);
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> listEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
}
