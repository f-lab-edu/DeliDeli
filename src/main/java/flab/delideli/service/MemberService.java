package flab.delideli.service;

import flab.delideli.dto.LoginDTO;
import flab.delideli.dto.MemberDTO;

import java.security.NoSuchAlgorithmException;

public interface MemberService {

    public void joinMember(MemberDTO member) throws NoSuchAlgorithmException;
    public boolean isExistUserId(String userid);
    public boolean login(LoginDTO loginDTO) throws NoSuchAlgorithmException;
    public String createSessionId(String userid);
}
