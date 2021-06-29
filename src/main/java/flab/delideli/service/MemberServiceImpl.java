package flab.delideli.service;

import flab.delideli.dao.MemberDao;
import flab.delideli.dto.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void joinMember(MemberDto member) {
        if(this.isExistUserId(member.getUserid()))
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberDao.joinMember(member);
    }

    @Override
    public boolean isExistUserId(String userid) {
        return memberDao.isExistUserId(userid);
    }
}
