package flab.delideli.service;

import flab.delideli.domain.MemberDTO;
import flab.delideli.repository.MemberRepository;

public class MemberJoinServiceImpl implements MemberJoinService {

    private final MemberRepository memberRepository;

    public MemberJoinServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(MemberDTO memberDTO) {
        memberRepository.save(memberDTO);
    }
}
