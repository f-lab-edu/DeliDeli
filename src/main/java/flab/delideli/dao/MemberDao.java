package flab.delideli.dao;

import flab.delideli.dto.LoginDTO;
import flab.delideli.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDao {

    public void joinMember(MemberDTO member);

    public boolean isExistUserId(String userid);

    public MemberDTO checkUserId(String userid);

    public MemberDTO checkPassword(CharSequence password);

    public MemberDTO findbyUserid(String loginid);

}
