package com.example.encryption;

import com.example.encryption.model.ExchangeKeyRequest;
import com.example.encryption.model.MockStorage;
import com.example.encryption.model.SymmetricKey;
import com.example.encryption.service.EncryptionService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/encryption/exchange")
/*@CrossOrigin(origins = "*")*/
public class EncryptionController {

    @Autowired
    EncryptionService encryptionService;
    @Autowired
    MockStorage mockStorage;

    @PostMapping
    public SymmetricKey exchange(@RequestBody ExchangeKeyRequest exchangeKeyRequest) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        String attributeId = UUID.randomUUID().toString();
        String encAES = encryptionService.createSecretKey(attributeId, exchangeKeyRequest.getPublicKey());
        return new SymmetricKey(attributeId, encAES);
    }
}
