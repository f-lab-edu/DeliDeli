package flab.delideli.interceptor;

import flab.delideli.exception.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptorImpl implements HandlerInterceptor {

    private static final String USER_ID = "USER_ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String currentUserID = getSessionUserId(request);

        if (currentUserID != null) {
            return true;
        }
        throw new UnauthorizedException();
    }

    private String getSessionUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String CurrentUserId = (String) session.getAttribute(USER_ID);

        return CurrentUserId;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
