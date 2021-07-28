package flab.delideli.service;

import flab.delideli.domain.MemberDTO;

public interface MemberService {

    MemberDTO selectMember(Long id);

    boolean isDuplicatedUserId(String userId);

    int joinMember(MemberDTO memberDTO);

    int updateMember(MemberDTO memberDTO);

    int deleteMember(Long id);

    Long getId(MemberDTO memberDTO);

    void initDB(Long id);

}
