package flab.delideli.service;

import flab.delideli.dto.LoginDTO;
import flab.delideli.dto.MemberDTO;

public interface MemberService {

    public void joinMember(MemberDTO member);

    public boolean isExistUserId(String userid);

    public boolean isExistUserInfo(LoginDTO loginDTO);

}
