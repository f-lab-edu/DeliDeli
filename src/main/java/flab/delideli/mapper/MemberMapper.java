package flab.delideli.mapper;

import flab.delideli.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

public interface MemberMapper {

    MemberDTO selectMember(Long id);

    boolean isDuplicatedUserId(String userId);

    int joinMember(MemberDTO memberDTO);

    int updateMember(MemberDTO memberDTO);

    int deleteMember(Long id);

    Long getId(MemberDTO memberDTO);

    void initDB(Long id);

}