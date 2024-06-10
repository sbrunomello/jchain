package com.example.jchain.attendance.model;

import com.example.jchain.blockchain.model.Transaction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Attendance extends Transaction {
    private String employeeId;
    private String dateTime;
    private String location;

    public Attendance(String employeeId, String location) {
        this.setEmployeeId(employeeId);
        this.setLocation(location);
        this.setTimestamp(System.currentTimeMillis());
        this.setDateTime(getFormattedDateTime());
    }

    private static String getFormattedDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}


