package com.example.jchain.blockchain.controller;

import com.example.jchain.blockchain.model.Block;
import com.example.jchain.blockchain.model.node.BlockchainMessage;
import com.example.jchain.blockchain.model.node.MessageType;
import com.example.jchain.blockchain.model.node.Peer;
import com.example.jchain.blockchain.service.BlockchainService;
import com.example.jchain.blockchain.service.BlockchainNodeService;
import com.example.jchain.controller.BaseController;
import com.example.jchain.util.IPFSService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ipfs.multibase.Base58;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/blockchain")
public class BlockchainController extends BaseController {

    private final BlockchainService blockchainService;
    private final BlockchainNodeService blockchainNodeService;
    private final IPFSService ipfsService;

    public BlockchainController(BlockchainService blockchainService, BlockchainNodeService blockchainNodeService, IPFSService ipfsService) {
        this.blockchainService = blockchainService;
        this.blockchainNodeService = blockchainNodeService;
        this.ipfsService = ipfsService;
    }

    @GetMapping("/blocks")
    public List<Block<?>> getBlockchain() {
        return blockchainService.getChain();
    }

    @GetMapping("/nodes")
    public List<Peer> getNodes() {
        return blockchainNodeService.getNodes();
    }

    @PostMapping("/nodes")
    public void addNode(@RequestBody Peer peer) {
        blockchainNodeService.addNode(peer);
    }

    @GetMapping("/test/{hash}")
    public String testGetNodesAndIPFS(@PathVariable String hash) {
        try {
            String byteIPFS = ipfsService.getFile(hash);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(byteIPFS);

            BlockchainMessage message = new BlockchainMessage(MessageType.SYNC_BLOCKCHAIN, json);
            blockchainNodeService.broadcastMessage(message);

            return json;

        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao adicionar bloco e sincronizar: " + e.getMessage();
        }
    }
}
