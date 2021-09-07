package flab.delideli.service;

import flab.delideli.dao.LoginDao;
import flab.delideli.dto.LoginDTO;
import flab.delideli.encrypt.Encryption;
import flab.delideli.exception.WrongLoginInfoException;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

	private final HttpSession session;
	private final Encryption encryption;
	private final LoginDao loginDao;
	private static final String LOGIN_ID = "LOGIN_ID";

	@Override
	public void login(LoginDTO loginDTO) {
		loginInfoCheck(loginDTO);
		session.setAttribute(LOGIN_ID, loginDTO);
	}

	@Override
	public void logout() {
		session.removeAttribute(LOGIN_ID);
	}

	public void loginInfoCheck(LoginDTO loginDTO) {

		String loginId = loginDTO.getLoginid();
		String loginPassword = encryption.encrypt(loginDTO.getLoginPassword());

		boolean isExistInfo = loginDao.isExistUserInfo(loginId, loginPassword);

		if(!isExistInfo) {
			throw new WrongLoginInfoException("아이디 혹은 비밀번호가 일치하지 않습니다.");
		}

	}

}