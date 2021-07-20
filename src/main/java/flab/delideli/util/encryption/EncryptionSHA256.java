package flab.delideli.util.encryption;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class EncryptionSHA256 implements Encryption {

    private static final int SALT_SIZE = 16;

    // SHA-256과 salt 값을 사용한 해싱 반복 수행 후 String 타입으로 리턴
    public String getHashing(String userPassword, String salt) {

        byte[] password = null;
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("SHA-256");

            for(int i = 0; i < 1000; i++) {
                String temp = userPassword + salt;
                md.update(temp.getBytes(StandardCharsets.UTF_8));
                password = md.digest();
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return byteToString(password);

    }

    // 임의의 salt 값을 생성 후 String 타입으로 리턴
    public String getSalt() {

        SecureRandom random = new SecureRandom();

        byte[] randomSalt = new byte[SALT_SIZE];
        random.nextBytes(randomSalt);

        return byteToString(randomSalt);

    }

    // 바이트를 16진수로 변환 후 String 타입으로 리턴
    public String byteToString(byte[] temp) {

        StringBuilder sb = new StringBuilder();

        for(byte a : temp) {
            sb.append(String.format("%02x", a));
        }

        return sb.toString();

    }

}
