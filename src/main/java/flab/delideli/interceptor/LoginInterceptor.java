package flab.delideli.interceptor;

import flab.delideli.exception.UnauthorizedException;
import flab.delideli.service.LoginService;
import flab.delideli.service.SessionLoginService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    @Qualifier("SessionLogin")
    private LoginService SessionloginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String currentUserId = SessionloginService.getSessionUserId(request);
        if (currentUserId != null) {
            return true;
        }
        throw new UnauthorizedException();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
