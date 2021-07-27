package flab.delideli.service;

import flab.delideli.domain.RequestLoginDTO;
import flab.delideli.domain.MemberDTO;
import flab.delideli.mapper.MemberMapper;
import flab.delideli.util.encryption.Encryption;
import flab.delideli.util.encryption.UserSalt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final Encryption encryption;
    private final UserSalt userSalt;

    private static final String ID = "id";

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
        String salt = userSalt.getRandomSalt();

        MemberDTO copyMemberDTO = new
                MemberDTO(memberDTO.getUserId(), memberDTO.getUserName(),
                encryption.getHashing(memberDTO.getUserPassword(), salt),
                memberDTO.getUserPhone(), memberDTO.getUserAddress());

        userSalt.addUserSalt(copyMemberDTO.getUserId(), salt);

        return memberMapper.joinMember(copyMemberDTO);

    }

    @Override
    public boolean isLoginCheck(RequestLoginDTO loginDTO) {

        String salt = userSalt.getUserSalt(loginDTO.getUserId());
        String userId = loginDTO.getUserId();
        String hashPassword = encryption.getHashing(loginDTO.getUserPassword(), salt);

        return memberMapper.isLoginCheck(userId, hashPassword);

    }

    @Override
    public void login(HttpSession session, Long id) {
        session.setAttribute(ID, id);
    }

    @Override
    public void logout(HttpSession session) {
        session.invalidate();
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
    public Long getId(String userId) {
        return memberMapper.getId(userId);
    }

    @Override
    public void initDB(Long id) {
        memberMapper.initDB(id);
    }

}
