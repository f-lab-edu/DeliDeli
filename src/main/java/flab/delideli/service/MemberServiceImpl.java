package flab.delideli.service;

import flab.delideli.domain.MemberDTO;
import flab.delideli.mapper.MemberMapper;
import flab.delideli.util.PasswordEncryption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncryption passwordEncryption;

    @Override
    public MemberDTO selectMember(Long id) {
        return memberMapper.selectMember(id);
    }

    @Override
    public boolean userIdCheck(String userId) {
        return memberMapper.userIdCheck(userId);
    }

    // 아이디 중복 체크
    public void validateUserId(String userId) throws IllegalStateException {
        if (userIdCheck(userId)) {
            throw new IllegalStateException("이미 가입된 아이디입니다.");
        }
    }

    // 아이디 중복 체크와 비밀번호 암호화를 거친 후 회원 가입 진행
    @Override
    public int joinMember(MemberDTO memberDTO) throws NoSuchAlgorithmException {

        validateUserId(memberDTO.getUserId());

        MemberDTO copyMemberDTO = new
                MemberDTO(memberDTO.getUserId(), memberDTO.getUserName(),
                passwordEncryption.getHashing(memberDTO.getUserPassword(), passwordEncryption.getSalt()),
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
