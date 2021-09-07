package flab.delideli.service;

import flab.delideli.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service("SessionLogin")
public class SessionLoginService implements LoginService {

    private static final String USER_ID = "USER_ID";

    public String getSessionUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentUserId = (String) session.getAttribute(USER_ID);

        return currentUserId;
    }

}
