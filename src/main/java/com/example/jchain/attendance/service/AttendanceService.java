package com.example.jchain.attendance.service;

import com.example.jchain.attendance.model.AttendanceType;
import com.example.jchain.blockchain.model.Transaction;
import com.example.jchain.blockchain.service.BlockchainService;
import com.example.jchain.employee.service.EmployeeService;
import com.example.jchain.attendance.model.Attendance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AttendanceService {


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BlockchainService blockchainService;

    public boolean registerAttendance(String employeeId, String key, String location) {
        if (employeeService.validateKey(employeeId, key)) {
            Attendance attendance = new Attendance(employeeId, location);
            //attendance.setType(AttendanceType.ATTENDANCE);
            blockchainService.addBlock(attendance, employeeId);
            log.info("Attendance registered for employee: {}", employeeId);
            return true;
        }
        log.warn("Invalid key for employee: {}", employeeId);
        return false;
    }

    public List<Transaction> getAttendanceByEmployee(String employeeId) {
        return blockchainService.getTransactions().stream()
                .filter(transaction -> transaction instanceof Attendance)
                .filter(transaction -> ((Attendance) transaction).getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }
}
