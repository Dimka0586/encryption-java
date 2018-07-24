package com.example.encryption.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

@Component
public class MockStorage {

    private Map<String, SecretKey> aesMap = new HashMap<>();
    private Map<String, Attribute> attributes = new HashMap<>();


    public MockStorage() {
    }

    public void putAes(String attributeId, SecretKey aes) {
        aesMap.put(attributeId, aes);
    }

    public void putAttribute(String attributeId, Attribute attribute) {
        attributes.put(attributeId, attribute);
    }

    public Map<String, SecretKey> getAesMap() {
        return aesMap;
    }

    public Map<String, Attribute> getAttributes() {
        return attributes;
    }
}
