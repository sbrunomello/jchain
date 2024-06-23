package com.example.jchain.blockchain.model;

import com.example.jchain.util.Util;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Block<T> {
    private int index;
    private String previousHash;
    private long timestamp;
    private Data<T> data;
    private String hash;

    public Block(int index, String previousHash, Data<T> data) {
        this.index = index;
        this.previousHash = previousHash;
        this.timestamp = Instant.now().toEpochMilli();
        this.data = data;
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String input = index + previousHash + timestamp + data.toString();
        return Util.applySha256(input);
    }
}
