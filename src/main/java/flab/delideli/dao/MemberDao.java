package flab.delideli.dao;

import flab.delideli.dto.MemberDTO;
import flab.delideli.enums.UserLevel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

    void joinMember(MemberDTO member);

    boolean isExistUserId(String userId);

    boolean isExistUserInfo(String loginId, String loginPassword);

    UserLevel selectUserLevel(String loginId);

    MemberDTO findByUserId(String loginId);

    void clearData(MemberDTO memberDTO);
}
