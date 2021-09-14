package flab.delideli.service;

import flab.delideli.dto.MemberDTO;

public interface MemberService {

    void joinMember(MemberDTO member);

    boolean isExistUserId(String userId);

}
