package flab.delideli.interceptor;

import flab.delideli.annotation.UserAuthorization;
import flab.delideli.exception.UnauthorizedException;
import flab.delideli.service.LoginService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	private LoginService sessionLoginService;

	public LoginInterceptor(@Qualifier("sessionLoginService") LoginService sessionLoginService) {
		this.sessionLoginService = sessionLoginService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String currentUserId = sessionLoginService.getSessionUserId();
		if (currentUserId != null) {
			return true;
		}

		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		UserAuthorization userAuthorization = handlerMethod.getMethodAnnotation(UserAuthorization.class);
		String role = userAuthorization.role().name();
		String userLevel = sessionLoginService.getSessionUserLevel();

		if (userAuthorization == null || (userAuthorization != null && role.equals(userLevel))) {
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