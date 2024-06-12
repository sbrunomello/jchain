package com.example.jchain.blockchain.service;

import com.example.jchain.blockchain.model.Block;
import com.example.jchain.blockchain.model.node.BlockchainMessage;
import com.example.jchain.blockchain.model.node.Peer;
import com.example.jchain.util.IPFSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BlockchainNodeServiceImpl implements BlockchainNodeService {

    private final List<Peer> nodes = new ArrayList<>();
    private final BlockchainService blockchainService;
    private final IPFSService ipfsService;

    @Autowired
    public BlockchainNodeServiceImpl(BlockchainService blockchainService, IPFSService ipfsService) {
        this.blockchainService = blockchainService;
        this.ipfsService = ipfsService;
        // Inicializar nós (peers)
        nodes.add(new Peer("127.0.0.1", 8081)); // Adicionar outros peers conforme necessário
    }

    @Override
    public void broadcastMessage(BlockchainMessage message) {
        for (Peer node : nodes) {
            // Lógica para enviar mensagem para cada nó
            log.info("Broadcasting message to node: " + node.getIpAddress() + ":" + node.getPort());
            // Aqui você pode implementar a lógica de envio HTTP ou usar WebSockets para enviar a mensagem
            handleMessage(message);
        }
    }

    @Override
    public void handleMessage(BlockchainMessage message) {
        log.info("Handling message of type: " + message.getType());
        switch (message.getType()) {
            case ADD_BLOCK:
                try {
                    byte[] blockData = ipfsService.getFile(message.getData()).getBytes();
                    String blockString = new String(blockData);
                    Block block = new Block();  // Construa o bloco a partir da string recebida (use um método adequado para isso)
                    // Adicione o bloco à blockchain local
                    blockchainService.getChain().add(block);
                } catch (IOException e) {
                    log.error("erro: {}", e.getMessage());
                }
                break;
            default:
                log.warn("Tipo de mensagem não suportado: {}", message.getType());
        }
    }

    @Override
    public List<Peer> getNodes() {
        return nodes;
    }

    @Override
    public void addNode(Peer peer) {
        nodes.add(peer);
        log.info("Node added: " + peer.getIpAddress() + ":" + peer.getPort());
    }
}
