package flab.delideli.service;

import flab.delideli.dto.LoginDTO;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

	private final HttpSession session;
	private static final String LOGIN_ID = "LOGIN_ID";

	@Override
	public void login(LoginDTO loginDTO) {
		session.setAttribute(LOGIN_ID, loginDTO.getLoginid());
	}

	@Override
	public void logout() {
		session.removeAttribute(LOGIN_ID);
	}

}
