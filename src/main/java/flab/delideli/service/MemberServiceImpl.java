package flab.delideli.service;

import flab.delideli.dao.MemberDao;
import flab.delideli.dto.MemberDTO;
import flab.delideli.encrypt.Encryption;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;
    private final Encryption encryptPassword;

    @Override
    public void joinMember(MemberDTO member) {
        if(this.isExistUserId(member.getUserid()))
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        MemberDTO encodemember = new MemberDTO(member.getUserid(), encryptPassword.encrypt(member.getPassword()), member.getUsername(), member.getPhone(), member.getAddress());
        memberDao.joinMember(encodemember);
    }

    @Override
    public boolean isExistUserId(String userid) {
        return memberDao.isExistUserId(userid);
    }

}
