package com.example.jchain.blockchain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction<T> {
    private long timestamp;
    private T data;
    private String txHash;
}

