package flab.delideli.service;

import flab.delideli.dto.LoginDTO;
import flab.delideli.dto.MemberDTO;

import java.security.NoSuchAlgorithmException;

public interface MemberService {

    public void joinMember(MemberDTO member);
    public boolean isExistUserId(String userid);
    public boolean login(LoginDTO loginDTO);
    public String createSessionId(String userid);
    public boolean isExistUserSession(String userid);
    public double getSessionId(String userid);
}
