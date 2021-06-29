package flab.delideli.service;

import flab.delideli.domain.MemberDTO;

import java.security.NoSuchAlgorithmException;

public interface MemberService {

    // 회원 조회
    MemberDTO selectMember(Long id);

    // 회원 아이디 중복 체크
    int userIdCheck(String userId);

    // 회원 가입
    int joinMember(MemberDTO memberDTO) throws NoSuchAlgorithmException;

    // 회원 정보 수정
    int updateMember(MemberDTO memberDTO);

    // 회원 탈퇴
    int deleteMember(Long id);

    // (테스트) DB에 저장된 기본키인 id 값을 불러옴
    Long getId(MemberDTO memberDTO);

    // (테스트) 반복 테스트를 위한 DB 초기화
    void initDB();

    // (암호화) SHA-256과 salt 값을 사용한 해싱 반복 수행 후 String 타입으로 리턴
    String getHashing(String userPassword, String salt) throws
            NoSuchAlgorithmException;

    // (암호화) 임의의 salt 값을 생성 후 String 타입으로 리턴
    String getSalt();

    // (암호화) 바이트를 16진수로 변환 후 String 타입으로 리턴
    String byteToString(byte[] temp);

}
