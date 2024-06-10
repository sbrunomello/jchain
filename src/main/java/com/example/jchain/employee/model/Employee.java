package com.example.jchain.employee.model;

import com.example.jchain.blockchain.model.Transaction;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Employee extends Transaction {
    private String id;
    private String name;
    private String key;
    private String email;
    private String department;
    private String position;
    private String dateOfJoining;
}


