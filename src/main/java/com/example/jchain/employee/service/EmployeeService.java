package com.example.jchain.employee.service;

import com.example.jchain.blockchain.service.BlockchainService;
import com.example.jchain.employee.model.enums.EmployeeType;
import com.example.jchain.employee.model.request.EmployeeRequest;
import com.example.jchain.util.Util;
import com.example.jchain.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private BlockchainService blockchainService;

    public Employee createEmployee(EmployeeRequest employeeRequest) {
        long timestamp = System.currentTimeMillis();
        String id = Util.applySha256(employeeRequest.getEmail() + timestamp);
        String key = Util.applySha256(id + timestamp);
        Employee employee = Employee.builder()
                .id(id)
                .name(employeeRequest.getName())
                .key(key)
                .email(employeeRequest.getEmail())
                .department(employeeRequest.getDepartment())
                .position(employeeRequest.getPosition())
                .dateOfJoining(employeeRequest.getDateOfJoining())
                .build();

        employee.setType(EmployeeType.CREATE_KEY);
        employee.setTimestamp(timestamp);
        blockchainService.addBlock(employee, id);
        return employee;
    }

    public Employee getEmployeeById(String id) {
        return blockchainService.getEmployees().stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean validateKey(String id, String key) {
        Employee employee = getEmployeeById(id);
        return employee != null && employee.getKey().equals(key);
    }

    public List<Employee> getAllEmployees() {
        return blockchainService.getEmployees();
    }
}
