package flab.delideli.service;

import flab.delideli.domain.MemberDTO;
import flab.delideli.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public List<MemberDTO> selectMemberList() {
        return memberMapper.selectMemberList();
    }

    @Override
    public MemberDTO selectMember(Long id) {
        return memberMapper.selectMember(id);
    }

    @Override
    public int userIdCheck(MemberDTO memberDTO) {
        return memberMapper.userIdCheck(memberDTO);
    }

    public void userIdDuplicateCheck(MemberDTO memberDTO) {
        if (userIdCheck(memberDTO) == 1) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Override
    public int joinMember(MemberDTO memberDTO) {
        userIdDuplicateCheck(memberDTO);
        return memberMapper.joinMember(memberDTO);
    }

    @Override
    public int updateMember(MemberDTO memberDTO) {
        return memberMapper.updateMember(memberDTO);
    }

//    @Override
//    public int initMember() {
//        return memberMapper.initMember();
//    }

    @Override
    public int deleteMember(Long id) {
        return memberMapper.deleteMember(id);
    }

}
