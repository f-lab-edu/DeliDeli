package flab.delideli.dao;

import flab.delideli.dto.LoginDTO;
import flab.delideli.dto.MemberDTO;
import flab.delideli.dto.UpdateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDao {

    public void joinMember(MemberDTO member);

    public boolean isExistUserId(String userid);

    public MemberDTO findbyUserid(String loginid);

    public void updateUser(@Param("userid") String userid, @Param("updateDTO") UpdateDTO updateDTO);

    public void deleteUser(String userid);
}
