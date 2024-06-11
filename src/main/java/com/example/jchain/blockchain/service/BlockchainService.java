package com.example.jchain.blockchain.service;

import com.example.jchain.attendance.model.Attendance;
import com.example.jchain.blockchain.model.Block;
import com.example.jchain.blockchain.model.Transaction;
import com.example.jchain.employee.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlockchainService {
    private static final Logger logger = LoggerFactory.getLogger(BlockchainService.class);

    @Getter
    private List<Block<?>> chain;
    @Getter
    private final List<Employee> employees;
    @Getter
    private final List<Transaction> transactions;
    private static final String FILENAME = "src/main/resources/blockchain_data/blockchain.json";
    private final ObjectMapper objectMapper;

    public BlockchainService() {
        this.chain = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        loadChain();
        if (chain.isEmpty()) {
            chain.add(createGenesisBlock());
            saveChain();
        }
    }

    private Block<String> createGenesisBlock() {
        return new Block<>(0, "0", "Genesis Block", "0");
    }

    public Block<?> getLatestBlock() {
        return chain.getLast();
    }

    public <T> void addBlock(T data, String userId) {
        Block<T> newBlock = new Block<>(chain.size(), getLatestBlock().getHash(), data, userId);
        chain.add(newBlock);
        if (data instanceof Employee) {
            employees.add((Employee) data);
        }
        if (data instanceof Attendance) {
            transactions.add((Transaction) data);
        } else if (data instanceof Transaction) {
            transactions.add((Transaction) data);
        }
        logger.info("Add new Block: {}", newBlock);
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

    private void saveChain() {
        try {
            objectMapper.writeValue(new File(FILENAME), chain);
        } catch (IOException e) {
            logger.error("Error saving blockchain: {}", e.getMessage());
        }
    }

    private void loadChain() {
        try {
            File file = new File(FILENAME);
            if (file.exists()) {
                chain = objectMapper.readValue(file, new TypeReference<List<Block<?>>>() {});
                for (Block<?> block : chain) {
                    if (block.getData() instanceof Employee) {
                        employees.add((Employee) block.getData());
                    } else if (block.getData() instanceof Transaction) {
                        transactions.add((Transaction) block.getData());
                    }
                }
            } else {
                chain = new ArrayList<>();
            }
        } catch (IOException e) {
            logger.error("Error loading blockchain: {}", e.getMessage());
            chain = new ArrayList<>();
        }
    }
}
