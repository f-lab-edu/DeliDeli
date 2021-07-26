package flab.delideli.service;

import flab.delideli.dto.LoginDTO;
import flab.delideli.dto.MemberDTO;

public interface MemberService {

    public void joinMember(MemberDTO member);

    public boolean isExistUserId(String userid);

    public boolean isExistUserInfo(LoginDTO loginDTO);

    //테스트케이스 작성을 위한 메소드
    public void deleteData(MemberDTO memberDTO);

}
