package com.example.encryption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class EncryptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncryptionApplication.class, args);
	}

	@PostConstruct
	public void init() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

		// encryptMessage();
	}

	/*public void encryptMessage() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

		String encryptionKeyString =  "thisisa128bitkey";
		String originalMessage = "This is a secret message";
		byte[] encryptionKeyBytes = encryptionKeyString.getBytes();

		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		SecretKey secretKey = new SecretKeySpec(encryptionKeyBytes, "AES");
    	cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		byte[] encryptedMessageBytes = cipher.doFinal(new String("hello").getBytes());

    	cipher.init(Cipher.DECRYPT_MODE, secretKey);

		byte[] decryptedMessageBytes = cipher.doFinal(encryptedMessageBytes);

	}*/


}
