package flab.delideli.mapper;

import flab.delideli.domain.MemberDTO;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {

    // 회원 조회
    MemberDTO selectMember(Long id);

    // 회원 아이디 중복 체크
    boolean userIdCheck(String userId);

    // 회원 가입
    int joinMember(MemberDTO memberDTO);

    int addUserSalt(@Param("userId") String userId, @Param("salt") String salt);

    String getUserSalt(String userId);

    boolean isLoginCheck(@Param("userId") String userId, @Param("hashPassword") String hashPassword);

    // 회원 정보 수정
    int updateMember(MemberDTO memberDTO);

    // 회원 탈퇴
    int deleteMember(Long id);

    // (테스트) DB에 저장된 기본키인 id 값을 불러옴
    Long getId(String userId);

    // (테스트) 반복 테스트를 위한 DB 초기화
    void initDB(Long id);

}