package flab.delideli.service;

import flab.delideli.dao.MemberDao;
import flab.delideli.dto.LoginDTO;
import flab.delideli.encrypt.Encrypt;
import flab.delideli.enums.UserLevel;
import flab.delideli.exception.UnauthorizedException;
import flab.delideli.exception.WrongLoginInfoException;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

	private final HttpSession session;
	private final Encrypt encryption;
	private final MemberDao memberDao;

	private static final String USER_ID = "USER_ID";
	private static final String USER_LEVEL = "USER_LEVEL";

	@Override
	public void login(LoginDTO loginDTO) {

		loginInfoCheck(loginDTO);

		String loginId = loginDTO.getLoginId();
		UserLevel userLevel = memberDao.selectUserLevel(loginId);

		if(userLevel.name().equals("OWNER_LEVEL")) {

			boolean isDocsSubmitted = memberDao.isDocsSubmitted(loginId);
			boolean isLoginApproved = memberDao.isLoginApproved(loginId);

			if (!isDocsSubmitted) {
				throw new UnauthorizedException("서류 제출이 완료되지 않았습니다.");
			} else if (!isLoginApproved) {
				throw new UnauthorizedException("관리자의 승인을 기다려야 합니다.");
			}

		}

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

	@Override
	public String getSessionUserId() {

		if(session.getAttribute(USER_ID) == null) {
			throw new UnauthorizedException("로그인 후 이용 가능합니다.");
		}
		return session.getAttribute(USER_ID).toString();

	}

	@Override
	public String getSessionUserLevel() {

		if(session.getAttribute(USER_LEVEL) == null) {
			throw new UnauthorizedException("로그인 후 이용 가능합니다.");
		}
		return (String)session.getAttribute(USER_LEVEL);

	}

}