package com.example.encryption.rest;

import com.example.encryption.configurations.RestClientConfiguration;
import com.example.encryption.model.ExchangeKeyRequest;
import com.example.encryption.model.SymmetricKey;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
//@RestClientTest(EncryptionRestClient.class)
@ContextConfiguration(classes = {RestClientConfiguration.class, EncryptionRestClient.class, ObjectMapper.class})
public class EncryptionRestClientTest {
    @Autowired
    @Qualifier("blockchainRestTemplateHttp")
    private RestTemplate restTemplate;
    @Autowired
    private EncryptionRestClient encryptionRestClient;
    // @Autowired
    //private MockRestServiceServer server;
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        MockRestServiceServer server =
                MockRestServiceServer.bindTo(restTemplate).build();

        String symmetricKeyString =
                objectMapper.writeValueAsString(new SymmetricKey("attrId", "genKeyqwe"));

        server.expect(requestTo("http://localhost:8080/encryption/exchange"))
                .andRespond(withSuccess(symmetricKeyString, MediaType.APPLICATION_JSON));

    }

    @Test
    public void exchangeKey() {
        SymmetricKey symmetricKey = encryptionRestClient.exchangeKey(new ExchangeKeyRequest("attrId", "pubKey"));
        assertEquals(symmetricKey.getAttributeId(), "attrId");
        assertEquals(symmetricKey.getGeneratedKey(), "genKeyqwe");
    }
}