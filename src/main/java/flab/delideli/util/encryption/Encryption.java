package flab.delideli.util.encryption;

public interface Encryption {

	String getHashing(String userPassword, String salt);

	String byteToString(byte[] temp);

}
