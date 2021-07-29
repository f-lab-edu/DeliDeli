package flab.delideli.service;

import flab.delideli.domain.RequestLoginDTO;
import flab.delideli.domain.MemberDTO;

import javax.servlet.http.HttpSession;

public interface MemberService {

    MemberDTO selectMember(Long id);

    boolean isDuplicatedUserId(String userId);

    int joinMember(MemberDTO memberDTO);

    boolean isLoginCheck(RequestLoginDTO loginDTO);

    void login(HttpSession session, Long id);

    void logout(HttpSession session);

    int updateMember(MemberDTO memberDTO);

    int deleteMember(Long id);

    Long getId(String userId);

    void initDB(Long id);

}