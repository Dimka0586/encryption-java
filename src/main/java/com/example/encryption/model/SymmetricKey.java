package com.example.encryption.model;

public class SymmetricKey {

    private String attributeId;
    private String generatedKey;

    public SymmetricKey() {
    }

    public SymmetricKey(String generatedKey) {
        this.generatedKey = generatedKey;
    }

    public SymmetricKey(String attributeId, String generatedKey) {
        this.attributeId = attributeId;
        this.generatedKey = generatedKey;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public String getGeneratedKey() {
        return generatedKey;
    }

    public void setGeneratedKey(String generatedKey) {
        this.generatedKey = generatedKey;
    }
}
