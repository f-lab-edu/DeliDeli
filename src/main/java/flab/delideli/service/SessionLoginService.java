package flab.delideli.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service("SessionLogin")
public class SessionLoginService implements LoginService {

    private static final String USER_ID = "USER_ID";

    public String getSessionUserId(HttpServletRequest request) {
        return session.getAttribute(USER_ID).toString();
    }

    public String getSessionUserLevel(HttpServletRequest request) {
        return session.getAttribute(USER_LEVEL).name();
    }

}
