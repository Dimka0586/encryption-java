package com.example.encryption.model;

public class ExchangeKeyRequest {

    private String attributeId;
    private String publicKey;

    public ExchangeKeyRequest() {
    }

    public ExchangeKeyRequest(String attributeId, String publicKey) {
        this.attributeId = attributeId;
        this.publicKey = publicKey;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
