package com.example.jchain.blockchain.service;

import com.example.jchain.attendance.model.Attendance;
import com.example.jchain.blockchain.model.Block;
import com.example.jchain.blockchain.model.Data;
import com.example.jchain.employee.model.Employee;
import com.example.jchain.util.IPFSService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BlockchainService {

    @Getter
    private List<Block<?>> chain;
    @Getter
    private final List<Employee> employees;
    @Getter
    private final List<Data> data;
    private final ObjectMapper objectMapper;

    private final IPFSService ipfsService;

    public BlockchainService(IPFSService ipfsService) {
        this.ipfsService = ipfsService;
        this.chain = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.data = new ArrayList<>();
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        loadChain();
        if (chain.isEmpty()) {
            chain.add(createGenesisBlock());
            saveChain();
        }
    }

    private Block createGenesisBlock() {
        long timestamp = Instant.now().toEpochMilli();
        return new Block<>(0, "0", new Data(timestamp, "Genesis Block", ""));
    }

    public Block<?> getLatestBlock() {
        return chain.getLast();
    }

    public <T> void addBlock(Data<T> data) {
        Block<T> newBlock = new Block<>(chain.size(), getLatestBlock().getHash(), data);
        chain.add(newBlock);
        if (data instanceof Employee) {
            employees.add((Employee) data);
        }
        if (data instanceof Attendance) {
            this.data.add(data);
        } else if (data != null) {
            this.data.add(data);
        }
        log.info("Add new Block: {}", newBlock);
        saveChain();
    }

    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block<?> currentBlock = chain.get(i);
            Block<?> previousBlock = chain.get(i - 1);
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

    public void saveChain() {
        try {
            String blockHash = ipfsService.addFile(chain.toString().getBytes());

            //BlockchainMessage message = new BlockchainMessage(MessageType.ADD_BLOCK, blockHash);

           // blockchainNodeService.broadcastMessage(message);
            log.info("hash: {}", blockHash);

        } catch (IOException e) {
            log.error("Error saving blockchain: {}", e.getMessage());
        }
    }

    public String loadChain(String hash) {
        try {
            String byteIPFS = ipfsService.getFile(hash);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(byteIPFS);

            if (!json.isEmpty()) {
                chain = objectMapper.readValue(json, new TypeReference<>() {});
                for (Block<?> block : chain) {
                    if (block.getData() instanceof Employee) {
                        employees.add((Employee) block.getData());
                    } else if (block.getData() != null) {
                        data.add(block.getData());
                    }
                }

                //BlockchainMessage message = new BlockchainMessage(MessageType.SYNC_BLOCKCHAIN, json);
                //blockchainNodeService.broadcastMessage(message);

                return json;
            } else {
                chain = new ArrayList<>();
            }
        } catch (IOException e) {
            log.error("Error sync blockchain: {}", e.getMessage());
            chain = new ArrayList<>();
        }
        return hash;
    }
}
