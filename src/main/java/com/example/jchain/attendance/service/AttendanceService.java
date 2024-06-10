package com.example.jchain.attendance.service;

import com.example.jchain.attendance.model.enums.AttendanceType;
import com.example.jchain.blockchain.model.Transaction;
import com.example.jchain.blockchain.service.BlockchainService;
import com.example.jchain.employee.service.EmployeeService;
import com.example.jchain.attendance.model.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BlockchainService blockchainService;

    public boolean registerAttendance(String employeeId, String key, String location) {
        if (employeeService.validateKey(employeeId, key)) {
            Attendance attendance = new Attendance(employeeId, location);
            attendance.setType(AttendanceType.ATTENDANCE);
            blockchainService.addBlock(attendance, employeeId);
            return true;
        }
        return false;
    }

    public List<Transaction> getAttendanceByEmployee(String employeeId) {
        List<Transaction> transactionList = new ArrayList<>();
        for (Transaction transaction : blockchainService.getTransactions()) {
            if (transaction instanceof Attendance ) {
                if (((Attendance) transaction).getEmployeeId().equals(employeeId)) {
                    transactionList.add(transaction);
                }
            }
        }
        return transactionList;
    }
}
