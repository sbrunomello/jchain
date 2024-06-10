package com.example.jchain.blockchain.model;

import com.example.jchain.blockchain.model.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private long timestamp;
    private Enum type;
}

