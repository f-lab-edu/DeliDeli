package flab.delideli.service;

import flab.delideli.domain.MemberDTO;

import java.util.List;

public interface MemberService {

    // 회원 리스트 조회
    List<MemberDTO> selectMemberList();

    // 회원 조회
    MemberDTO selectMember(Long id);

    // 회원 아이디 중복 체크
    int userIdCheck(MemberDTO memberDTO);

    // 회원 가입
    int joinMember(MemberDTO memberDTO);

    // 회원 정보 수정
    int updateMember(MemberDTO memberDTO);

    // 회원 탈퇴
    int deleteMember(Long id);

    // 반복 test를 위한 DB 초기화
//    int initMember();

}
