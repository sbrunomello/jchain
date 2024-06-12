package com.example.jchain.blockchain.service;

import com.example.jchain.blockchain.model.node.BlockchainMessage;

public interface BlockchainMessageHandler {
    void handle(BlockchainMessage message);
}
