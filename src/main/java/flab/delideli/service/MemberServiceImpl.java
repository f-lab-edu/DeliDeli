package flab.delideli.service;

import flab.delideli.domain.MemberDTO;
import flab.delideli.mapper.MemberMapper;
import flab.delideli.util.encryption.EncryptionSHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final EncryptionSHA256 encryptionSHA256;

    @Override
    public MemberDTO selectMember(Long id) {
        return memberMapper.selectMember(id);
    }

    @Override
    public boolean isDuplicatedUserId(String userId) {
        return memberMapper.isDuplicatedUserId(userId);
    }

    public void validateUserId(String userId) {
        if (isDuplicatedUserId(userId)) {
            throw new IllegalStateException("이미 가입된 아이디입니다.");
        }
    }

    @Override
    public int joinMember(MemberDTO memberDTO) {

        validateUserId(memberDTO.getUserId());

        MemberDTO copyMemberDTO = new
                MemberDTO(memberDTO.getUserId(), memberDTO.getUserName(),
                encryptionSHA256.getHashing(memberDTO.getUserPassword(), encryptionSHA256.getSalt()),
                memberDTO.getUserPhone(), memberDTO.getUserAddress());

        return memberMapper.joinMember(copyMemberDTO);

    }

    @Override
    public int updateMember(MemberDTO memberDTO) {
        return memberMapper.updateMember(memberDTO);
    }

    @Override
    public int deleteMember(Long id) {
        return memberMapper.deleteMember(id);
    }

    @Override
    public Long getId(MemberDTO memberDTO) {
        return memberMapper.getId(memberDTO);
    }

    @Override
    public void initDB(Long id) {
        memberMapper.initDB(id);
    }

}
