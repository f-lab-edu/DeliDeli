package flab.delideli.interceptor;

import flab.delideli.annotation.LoginUserLevel;
import flab.delideli.dto.LoginDTO;
import flab.delideli.dto.OwnerDTO;
import flab.delideli.exception.UnauthorizedException;
import flab.delideli.service.LoginService;
import javax.servlet.http.HttpSession;
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

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginUserLevel loginUserLevel = handlerMethod.getMethodAnnotation(LoginUserLevel.class);
        if (loginUserLevel == null) {
            return true;
        }

        String currentUserId = SessionloginService.getSessionUserId(request);
        String role = loginUserLevel.role().toString();
        LoginDTO loginDTO = (LoginDTO) request.getSession().getAttribute("USER_ID");
        LoginUserLevel userLevel = loginDTO.getLoginUserLevel();
        if (currentUserId != null) {
            if ("OWNER_LEVEL".equals(role) && "OWNER_LEVEL".equals(userLevel)) {
                return true;
            }

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
