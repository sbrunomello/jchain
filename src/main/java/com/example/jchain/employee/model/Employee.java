package com.example.jchain.employee.model;

import com.example.jchain.blockchain.model.Data;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Employee extends Data {
    private String id;
    private String name;
    private String key;
    private String email;
    private String department;
    private String position;
    private String dateOfJoining;
}


