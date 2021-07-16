package flab.delideli.service;

import flab.delideli.dto.MemberDTO;

import java.security.NoSuchAlgorithmException;

public interface MemberService {

    public void joinMember(MemberDTO member);
    public boolean isExistUserId(String userid);
}
