package com.example.encryption.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Set;
import sun.security.rsa.RSAPublicKeyImpl;

@Service
public class EncryptionService {

    public EncryptionService() {
    }

    public String createSecretKey(String encodedPubKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        /*String encryptionKeyString =  "thisisa128bitkey";
        String originalMessage = "This is a secret message";
        byte[] encryptionKeyBytes = encryptionKeyString.getBytes();*/
        PublicKey publicKey = getPublicKey(encodedPubKey);
        SecretKey secretKey = generateAES();


        /*byte[] decodedPubKey = Base64.decodeBase64(encodedPubKey);
        System.out.println("encodedPuKey: " + encodedPubKey);
        System.out.println("decodedPuKey: " + decodedPubKey);
        *///byte[] nonceAndCounter = new byte[16];
        //IvParameterSpec ivSpec = new IvParameterSpec(nonceAndCounter);
        Cipher cipher = Cipher.getInstance("RSA");
        //System.out.println("length: " + Base64.decodeBase64(encodedPubKey).length);
        //SecretKey secretKey = new SecretKeySpec(Base64.decodeBase64(encodedPubKey),0, 32, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);


        return Base64.encodeBase64String(cipher.doFinal(secretKey.getEncoded()));
    }

    public String createAES(String pubKey) {
        //Security.addProvider(new BouncyCastleProvider());
        PublicKey publicKey = getPublicKey(pubKey);
        SecretKey secretKey = generateAES();
        byte[] result = null;
        byte[] nonceAndCounter = new byte[16];
        try {
            //IvParameterSpec ivSpec = new IvParameterSpec(nonceAndCounter);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.WRAP_MODE, publicKey);
            cipher.wrap(secretKey);
            result = cipher.doFinal(secretKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Base64.encodeBase64String(result);
    }

    public PublicKey getPublicKey(String pubKey) {
        byte[] publicBytes = Base64.decodeBase64(pubKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory = null;
        PublicKey publicKey = null;
        KeyGenerator keyGenerator = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            //keyGenerator = KeyGenerator.getInstance("RSA");
            //keyGenerator.init(128);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public SecretKey generateAES() {

        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyGenerator.generateKey();
    }

    public void getProviders() {
        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            System.out.println("provider: " + provider);
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                System.out.println("alg: " + service.getAlgorithm());
                // find algorithm and retrieve service information
            }
        }
    }
}
