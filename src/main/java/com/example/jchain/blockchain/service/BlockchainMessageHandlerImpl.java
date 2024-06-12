package com.example.jchain.blockchain.service;

import com.example.jchain.blockchain.model.node.BlockchainMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BlockchainMessageHandlerImpl implements BlockchainMessageHandler {

    @Override
    public void handle(BlockchainMessage message) {
        // Lógica para tratar mensagem recebida
        log.info("Received message of type: " + message.getType());
        // Implementar lógica de acordo com o tipo de mensagem
    }
}
