package flab.delideli.service;

import flab.delideli.domain.MemberDTO;
import flab.delideli.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    private static final int SALT_SIZE = 16;

    @Override
    public MemberDTO selectMember(Long id) {
        return memberMapper.selectMember(id);
    }

    @Override
    public int userIdCheck(String userId) {
        return memberMapper.userIdCheck(userId);
    }

    // 아이디 중복 체크
    public void validateUserIdDuplicateCheck(String userId) throws IllegalStateException {
        if (userIdCheck(userId) == 1) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 아이디 중복 체크와 비밀번호 암호화를 거친 후 회원 가입 진행
    @Override
    public int joinMember(MemberDTO memberDTO) throws NoSuchAlgorithmException {

        validateUserIdDuplicateCheck(memberDTO.getUserId());
        memberDTO.passwordEncoding(getHashing(memberDTO.getUserPassword(), getSalt()));

        return memberMapper.joinMember(memberDTO);

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
    public void initDB() {
        memberMapper.initDB();
    }

    @Override
    public String getHashing(String userPassword, String salt) throws
            NoSuchAlgorithmException {

        byte[] password = null;

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        for(int i = 0; i < 10000; i++) {
            String temp = userPassword + salt;
            md.update(temp.getBytes(StandardCharsets.UTF_8));
            password = md.digest();
        }

        return byteToString(password);

    }

    @Override
    public String getSalt() {

        SecureRandom random = new SecureRandom();

        byte[] randomSalt = new byte[SALT_SIZE];
        random.nextBytes(randomSalt);

        return byteToString(randomSalt);

    }

    @Override
    public String byteToString(byte[] temp) {

        StringBuilder sb = new StringBuilder();

        for(byte a : temp) {
            sb.append(String.format("%02x", a));
        }

        return sb.toString();

    }

}
