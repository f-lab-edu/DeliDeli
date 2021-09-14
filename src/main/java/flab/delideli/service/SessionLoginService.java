package flab.delideli.service;

import flab.delideli.dao.MemberDao;
import flab.delideli.dto.LoginDTO;
import flab.delideli.encrypt.Encryption;
import flab.delideli.enums.UserLevel;
import flab.delideli.exception.WrongLoginInfoException;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

	private final HttpSession session;
	private final Encryption encryption;
	private final MemberDao memberDao;

	private static final String USER_ID = "USER_ID";
	private static final String USER_LEVEL = "USER_LEVEL";

	@Override
	public void login(LoginDTO loginDTO) {

		loginInfoCheck(loginDTO);

		String loginId = loginDTO.getLoginId();
		UserLevel userLevel = memberDao.selectUserLevel(loginId);

		session.setAttribute(USER_ID, loginId);
		session.setAttribute(USER_LEVEL, userLevel);

	}

	@Override
	public void logout() {

		session.removeAttribute(USER_ID);
		session.removeAttribute(USER_LEVEL);

	}

	public void loginInfoCheck(LoginDTO loginDTO) {

		String loginId = loginDTO.getLoginId();
		String loginPassword = encryption.encrypt(loginDTO.getLoginPassword());

		boolean isExistInfo = memberDao.isExistUserInfo(loginId, loginPassword);

		if(!isExistInfo) {
			throw new WrongLoginInfoException("아이디 혹은 비밀번호가 일치하지 않습니다.");
		}

	}

}