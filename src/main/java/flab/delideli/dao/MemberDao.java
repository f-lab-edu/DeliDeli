package flab.delideli.dao;

import flab.delideli.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

    public void joinMember(MemberDTO member);
    public boolean isExistUserId(String userid);
    public MemberDTO checkUserId(String userid); //로그인시 체크
    public MemberDTO checkPassword(CharSequence password);//로그인시 체크
}
