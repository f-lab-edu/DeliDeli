package flab.delideli.dao;

import flab.delideli.dto.MemberDTO;
import flab.delideli.dto.UpdateDTO;
import flab.delideli.enums.UserLevel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDao {

    void joinMember(MemberDTO member);

    boolean isExistUserId(String userId);

    boolean isExistUserInfo(@Param("loginId") String loginId, @Param("loginPassword") String loginPassword);

    void insertOwnerId(String userId);

    boolean isDocsSubmitted(String loginId);

    boolean isLoginApproved(String loginId);

    MemberDTO findByUserId(String loginId);

    UserLevel selectUserLevel(String loginId);

    void updateOwnerDocsSubmission(String ownerId);

    void updateOwnerLoginApproval(String ownerId);

    void updateUser(@Param("userId") String userId, @Param("updateDTO") UpdateDTO updateDTO);

    void deleteUser(String userId);

}