package flab.delideli.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class LoginService {

    private static final String USER_ID = "USER_ID";

    public String getSessionUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String currentUserId = (String) session.getAttribute(USER_ID);

        return currentUserId;
    }

}
