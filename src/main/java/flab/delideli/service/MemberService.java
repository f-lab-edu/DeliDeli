package flab.delideli.service;

import flab.delideli.dto.MemberDto;

public interface MemberService {

    public void joinMember(MemberDto member);
    public boolean isExistUserId(String userid);
}
