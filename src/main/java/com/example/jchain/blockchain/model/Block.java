package com.example.jchain.blockchain.model;

import com.example.jchain.util.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Block<T> {
    private int index;
    private String previousHash;
    private long timestamp;
    private T data;
    private String hash;
    private String userId;

    public Block(int index, String previousHash, T data, String userId) {
        this.index = index;
        this.previousHash = previousHash;
        this.timestamp = Instant.now().toEpochMilli();
        this.data = data;
        this.userId = userId;
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String input = index + previousHash + timestamp + data.toString() + userId;
        return Util.applySha256(input);
    }
}
