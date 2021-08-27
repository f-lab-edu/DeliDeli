package flab.delideli.service;

import flab.delideli.dto.LoginDTO;
import flab.delideli.dto.MemberDTO;
import flab.delideli.dto.UpdateDTO;

public interface MemberService {

    public void joinMember(MemberDTO member);

    public boolean isExistUserId(String userid);

    public boolean isExistUserInfo(LoginDTO loginDTO);

    public void updateUserInfo(String userid, UpdateDTO updateDTO);

    public void deleteUserInfo(String userid);
}
