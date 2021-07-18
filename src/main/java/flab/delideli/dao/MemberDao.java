package flab.delideli.dao;

import flab.delideli.dto.LoginDTO;
import flab.delideli.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDao {

    public void joinMember(MemberDTO member);
    public boolean isExistUserId(String userid);
    public MemberDTO checkUserId(String userid); //로그인시 체크
    public MemberDTO checkPassword(CharSequence password);//로그인시 체크
    public MemberDTO findbyUserid(String loginid); //로그인하는 유저정보 가져오기
    public void createSessionid(@Param("userid") String userid, @Param("sessionid") double sessionid);
    public boolean isExistUserSession(String userid); //세션아이디가 이미 존재하는 지 체크
    public double getSessionId(String userid); // 이미 존재하는 세션아이디 가져오기
}
