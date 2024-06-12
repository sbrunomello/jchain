package com.example.jchain.blockchain.model.node;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Peer {
    private String ipAddress;
    private int port;
}
