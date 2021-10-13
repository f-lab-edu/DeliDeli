package flab.delideli.service;

import flab.delideli.dto.MemberDTO;
import flab.delideli.dto.UpdateDTO;

public interface MemberService {

    void joinMember(MemberDTO member);

    boolean isExistUserId(String userId);

    void setOwnerDocsSubmission(String ownerId);

    void setOwnerLoginApproval(String ownerId);

    public void updateUserInfo(String userid, UpdateDTO updateDTO);

    public void deleteUserInfo(String userid);
}
