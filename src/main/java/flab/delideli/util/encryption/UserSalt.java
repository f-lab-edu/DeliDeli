package flab.delideli.util.encryption;

import flab.delideli.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSalt {

	private final MemberMapper memberMapper;

	public int addUserSalt(String userId, String salt) {
		return memberMapper.addUserSalt(userId, salt);
	}

	public String getUserSalt(String userId) {
		return memberMapper.getUserSalt(userId);
	}

}
