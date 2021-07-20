package flab.delideli.service;

import flab.delideli.domain.RequestLoginDTO;
import flab.delideli.domain.MemberDTO;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

public interface MemberService {

    // 회원 조회
    MemberDTO selectMember(Long id);

    // 회원 아이디 중복 체크
    boolean userIdCheck(String userId);

    // 회원 가입
    int joinMember(MemberDTO memberDTO) throws NoSuchAlgorithmException;

    // 회원 고유의 솔트 값을 저장
    int addUserSalt(String userId, String salt);

    // 회원 고유의 솔트 값을 불러옴
    String getUserSalt(String userId);

    // 로그인하려는 회원의 정보가 존재하는지 체크
    boolean loginCheck(RequestLoginDTO loginDTO);

    void login(HttpSession session, String userId);

    void logout(HttpSession session);

    // 회원 정보 수정
    int updateMember(MemberDTO memberDTO);

    // 회원 탈퇴
    int deleteMember(Long id);

    // (테스트) DB에 저장된 기본키인 id 값을 불러옴
    Long getId(String userId);

    // (테스트) 반복 테스트를 위한 DB 초기화
    void initDB(Long id);

}
