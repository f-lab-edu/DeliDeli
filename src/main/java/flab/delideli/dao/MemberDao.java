package flab.delideli.dao;

import flab.delideli.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

    public void joinMember(MemberDto member);
    public boolean isExistUserId(String userid);
    
}
