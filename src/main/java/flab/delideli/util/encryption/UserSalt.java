package flab.delideli.util.encryption;

import flab.delideli.mapper.MemberMapper;
import java.security.SecureRandom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSalt {

	private static final int SALT_SIZE = 16;

	private final MemberMapper memberMapper;
	private final Encryption encryption;

	public String getRandomSalt() {

		SecureRandom random = new SecureRandom();

		byte[] randomSalt = new byte[SALT_SIZE];
		random.nextBytes(randomSalt);

		return encryption.byteToString(randomSalt);

	}

	public int addUserSalt(String userId, String salt) {
		return memberMapper.addUserSalt(userId, salt);
	}

	public String getUserSalt(String userId) {
		return memberMapper.getUserSalt(userId);
	}

}
