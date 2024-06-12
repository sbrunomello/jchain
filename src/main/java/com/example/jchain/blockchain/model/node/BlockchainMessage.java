package com.example.jchain.blockchain.model.node;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlockchainMessage {
    private MessageType type;
    private String data;
}
