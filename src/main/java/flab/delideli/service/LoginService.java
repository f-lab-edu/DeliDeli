package flab.delideli.service;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

	String getSessionUserId(HttpServletRequest request);

	String getSessionUserLevel(HttpServletRequest request);

}
