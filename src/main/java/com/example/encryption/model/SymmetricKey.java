package com.example.encryption.model;

public class SymmetricKey {

    private String generatedKey;

    public SymmetricKey() {
    }

    public SymmetricKey(String generatedKey) {
        this.generatedKey = generatedKey;
    }

    public String getGeneratedKey() {
        return generatedKey;
    }

    public void setGeneratedKey(String generatedKey) {
        this.generatedKey = generatedKey;
    }
}
