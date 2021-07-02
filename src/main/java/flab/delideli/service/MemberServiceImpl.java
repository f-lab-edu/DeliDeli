package flab.delideli.service;

import flab.delideli.dao.MemberDao;
import flab.delideli.dto.MemberDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;

    @Override
    public void joinMember(MemberDTO member) throws NoSuchAlgorithmException {
        if(this.isExistUserId(member.getUserid()))
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        MemberDTO encodemember = new MemberDTO(member.getUserid(), encrypt(member.getPassword()), member.getUsername(), member.getPhone(), member.getAddress());
        memberDao.joinMember(encodemember);
    }

    @Override
    public boolean isExistUserId(String userid) {
        return memberDao.isExistUserId(userid);
    }


    public String encrypt(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for(byte b: bytes) {
            builder.append(String.format("%02x",b));
        }
        return builder.toString();
    }
}
