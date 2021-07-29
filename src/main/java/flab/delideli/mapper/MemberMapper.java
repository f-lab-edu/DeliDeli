package flab.delideli.mapper;

import flab.delideli.domain.MemberDTO;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {

    MemberDTO selectMember(Long id);

    boolean isDuplicatedUserId(String userId);

    int joinMember(MemberDTO memberDTO);

    int addUserSalt(@Param("userId") String userId, @Param("salt") String salt);

    String getUserSalt(String userId);

    boolean isLoginCheck(@Param("userId") String userId, @Param("hashPassword") String hashPassword);

    int updateMember(MemberDTO memberDTO);

    int deleteMember(Long id);

    Long getId(String userId);

    void initDB(Long id);

}