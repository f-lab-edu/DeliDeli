package flab.delideli.encrypt;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import flab.delideli.controller.ExceptionAdvice;
import java.security.NoSuchAlgorithmException;

@Component
public class Encryption {

    public String encrypt(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("잘못된 암호화 알고리즘이 들어갔습니다.",e);
        }
        md.update(password.getBytes());
        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for(byte b: bytes) {
            builder.append(String.format("%02x",b));
        }
        return builder.toString();
    }
}
