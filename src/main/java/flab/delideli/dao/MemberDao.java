package flab.delideli.dao;

import flab.delideli.dto.MemberDTO;
import flab.delideli.enums.UserLevel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

    void joinMember(MemberDTO member);

    boolean isExistUserId(String userId);

    boolean isExistUserInfo(String loginId, String loginPassword);

    boolean isDocsSubmitted(String loginId);

    boolean isDocsApproved(String loginId);

    MemberDTO findByUserId(String loginId);

    UserLevel selectUserLevel(String loginId);

    void updateOwnerLoginApproval(String ownerId);

    void updateOwnerDocsSubmitted(String ownerId);

    void updateOwnerDocsApproved(String ownerId);

}
