package com.example.encryption.controllers;

import com.example.encryption.model.StrWrap;
import com.example.encryption.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/strWraps")
@CrossOrigin(origins = "*")
public class StrWrapController {

    private EncryptionService encryptionService;

    @Autowired
    public StrWrapController(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @PutMapping
    public void putStrWrap(@RequestBody StrWrap strWrap) {

    }
}
