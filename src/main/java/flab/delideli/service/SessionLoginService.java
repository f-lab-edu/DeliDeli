package flab.delideli.service;

import flab.delideli.exception.UnauthorizedException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionLoginService implements LoginService {

    private static final String USER_ID = "USER_ID";
    private static final String USER_LEVEL = "USER_LEVEL";

    public String getSessionUserId() {

        if(session.getAttribute(USER_ID) == null) {
            throw new UnauthorizedException("로그인 후 이용 가능합니다.");
        }
        return session.getAttribute(USER_ID).toString();

    }

    public String getSessionUserLevel() {

        if(session.getAttribute(USER_LEVEL) == null) {
            throw new UnauthorizedException("로그인 후 이용 가능합니다.");
        }
        return (String)session.getAttribute(USER_LEVEL);

    }

}