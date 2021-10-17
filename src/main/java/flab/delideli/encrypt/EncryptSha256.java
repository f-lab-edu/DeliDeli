package flab.delideli.encrypt;

import org.springframework.stereotype.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class EncryptSha256 implements Encrypt {

    @Override
    public String encrypt(String password) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("암호알고리즘을 찾을 수 없습니다.", e);
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