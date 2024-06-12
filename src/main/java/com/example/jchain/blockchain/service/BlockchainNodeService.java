package com.example.jchain.blockchain.service;

import com.example.jchain.blockchain.model.node.BlockchainMessage;
import com.example.jchain.blockchain.model.node.Peer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlockchainNodeService {
    void broadcastMessage(BlockchainMessage message);
    void handleMessage(BlockchainMessage message);
    List<Peer> getNodes();
    void addNode(Peer peer);
}
