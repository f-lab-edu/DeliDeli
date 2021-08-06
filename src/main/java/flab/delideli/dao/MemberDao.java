package flab.delideli.dao;

import flab.delideli.dto.LoginDTO;
import flab.delideli.dto.MemberDTO;
import flab.delideli.dto.UpdateDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

    public void joinMember(MemberDTO member);

    public boolean isExistUserId(String userid);

    public MemberDTO findbyUserid(String loginid);

    public void updateUser(UpdateDTO updateDTO);

}
