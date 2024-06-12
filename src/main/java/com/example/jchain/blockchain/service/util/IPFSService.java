package com.example.jchain.util;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class IPFSService {

    private final IPFS ipfs;

    public IPFSService() {
        this.ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
    }

    public String addFile(byte[] data) throws IOException {
        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(data);
        MerkleNode addResult = ipfs.add(file).get(0);
        return addResult.hash.toBase58();
    }

    public String getFile(String hash) throws IOException {
        Multihash filePointer = Multihash.fromBase58(hash);
        byte[] fileContents = ipfs.cat(filePointer);
        return new String(fileContents, StandardCharsets.UTF_8);
    }
}
