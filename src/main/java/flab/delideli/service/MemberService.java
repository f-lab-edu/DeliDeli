package flab.delideli.service;

import flab.delideli.dto.MemberDTO;

public interface MemberService {

    void joinMember(MemberDTO member);

    boolean isExistUserId(String userId);

    void setOwnerLoginApproval(String ownerId);

    void setOwnerDocsSubmitted(String ownerId);

    void setOwnerDocsApproved(String ownerId);

}
