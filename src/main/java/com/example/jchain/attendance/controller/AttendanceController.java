package com.example.jchain.attendance.controller;

import com.example.jchain.blockchain.model.Transaction;
import com.example.jchain.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/register")
    public ResponseEntity<String> registerAttendance(
            @RequestParam String employeeId,
            @RequestParam String key,
            @RequestParam String location) {
        boolean success = attendanceService.registerAttendance(employeeId, key, location);
        if (success) {
            return ResponseEntity.ok("Attendance registered successfully");
        } else {
            return ResponseEntity.status(403).body("Invalid key");
        }
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Transaction>> listAttendanceByEmployee(@PathVariable String employeeId) {
        List<Transaction> attendance = attendanceService.getAttendanceByEmployee(employeeId);
        return ResponseEntity.ok(attendance);
    }
}
