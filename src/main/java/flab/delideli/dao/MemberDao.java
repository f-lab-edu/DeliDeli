package flab.delideli.dao;

import flab.delideli.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

    public void memberjoin(MemberDto member);
}
