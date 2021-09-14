package flab.delideli.interceptor;

import flab.delideli.annotation.LoginUserLevel;
import flab.delideli.exception.UnauthorizedException;
import flab.delideli.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {


    private LoginService SessionloginService;

    @Autowired
    public LoginInterceptor(@Qualifier("SessionLogin") LoginService sessionloginService) {
        SessionloginService = sessionloginService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String currentUserId = SessionloginService.getSessionUserId(request);
        if (currentUserId != null) {
            return true;
        }

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginUserLevel loginUserLevel = handlerMethod.getMethodAnnotation(LoginUserLevel.class);
        String role = loginUserLevel.role().name();
        String userLevel = SessionloginService.getSessionUserLevel(request);

        if (loginUserLevel == null ||
            (loginUserLevel != null && role.equals(userLevel))) {
            return true;
        }

        throw new UnauthorizedException("접근 권한이 없습니다.");

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
