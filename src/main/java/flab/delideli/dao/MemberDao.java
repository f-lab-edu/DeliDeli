package flab.delideli.dao;

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
    public double getSessionid(@Param("userid") String userid);
}
