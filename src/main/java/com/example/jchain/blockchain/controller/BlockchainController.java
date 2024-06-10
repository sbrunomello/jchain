package com.example.jchain.blockchain.controller;

import com.example.jchain.blockchain.model.Block;
import com.example.jchain.blockchain.service.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blockchain")
public class BlockchainController {

    @Autowired
    private BlockchainService blockchainService;

    @GetMapping()
    public ResponseEntity<List<Block<?>>> viewBlockchain() {
        return ResponseEntity.ok(blockchainService.getChain());
    }
}
