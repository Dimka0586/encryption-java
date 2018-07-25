package com.example.encryption.rest;

import com.example.encryption.model.ExchangeKeyRequest;
import com.example.encryption.model.SymmetricKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EncryptionRestClient {

    private RestTemplate restTemplate;

    @Autowired
    public EncryptionRestClient(@Qualifier("blockchainRestTemplateHttp") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SymmetricKey exchangeKey(ExchangeKeyRequest exchangeKeyRequest) {
        HttpEntity<ExchangeKeyRequest> request = new HttpEntity<>(exchangeKeyRequest);
        ResponseEntity<SymmetricKey> response = restTemplate.exchange("/encryption/exchange",
                HttpMethod.POST, request, SymmetricKey.class);
        return response.getBody();
    }
}
