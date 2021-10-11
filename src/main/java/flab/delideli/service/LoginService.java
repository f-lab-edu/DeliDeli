package flab.delideli.service;

import flab.delideli.dto.LoginDTO;

public interface LoginService {

	void login(LoginDTO loginId);

	void logout();

	String getSessionUserId();

	String getSessionUserLevel();

}