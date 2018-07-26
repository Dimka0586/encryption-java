package com.example.encryption.controllers;

import com.example.encryption.model.Attribute;
import com.example.encryption.model.MockStorage;
import com.example.encryption.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/pds/attributes")
//@CrossOrigin(origins = "*")
public class AttributesController {

    @Autowired
    EncryptionService encryptionService;
    @Autowired
    MockStorage mockStorage;

    @PostMapping
    @RequestMapping(value = "/{attributeId}")
    public void putAttribute(@PathVariable("attributeId") String attributeId,
                             @RequestBody Attribute attribute) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidAlgorithmParameterException {
         System.out.println(attributeId);
         SecretKey aes = mockStorage.getAesMap().get(attributeId);
         System.out.println("aes: " + aes);
         encryptionService.decryptWithAES(attribute.getValue(), aes);
    }
}
