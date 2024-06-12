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
    private Transaction<T> transaction;
    private String hash;
    private String userId;

    public Block(int index, String previousHash, Transaction<T> transaction, String userId) {
        this.index = index;
        this.previousHash = previousHash;
        this.timestamp = Instant.now().toEpochMilli();
        this.transaction = transaction;
        this.userId = userId;
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String input = index + previousHash + timestamp + transaction.toString() + userId;
        return Util.applySha256(input);
    }
}
