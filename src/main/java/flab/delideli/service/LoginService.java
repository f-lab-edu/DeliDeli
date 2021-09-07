package flab.delideli.service;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    public String getSessionUserId(HttpServletRequest request);

    }
