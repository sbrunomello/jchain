package com.example.jchain.blockchain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data<T> {
    private long timestamp;
    private T data;
    private String owner;
}

