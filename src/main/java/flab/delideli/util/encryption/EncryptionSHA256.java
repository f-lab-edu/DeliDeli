package flab.delideli.util.encryption;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class EncryptionSHA256 implements Encryption {


    @Override
    public String getHashing(String userPassword, String salt) {

        byte[] password = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            for (int i = 0; i < 1000; i++) {
                String temp = userPassword + salt;
                md.update(temp.getBytes(StandardCharsets.UTF_8));
                password = md.digest();
            }
        } catch(NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return byteToString(password);

    }

    @Override
    public String byteToString(byte[] temp) {

        StringBuilder sb = new StringBuilder();

        for(byte a : temp) {
            sb.append(String.format("%02x", a));
        }

        return sb.toString();

    }

}
