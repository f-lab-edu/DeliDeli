package flab.delideli.service;

import flab.delideli.dao.MemberDao;
import flab.delideli.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;

    @Autowired
    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public void joinMember(MemberDto member) {
        if(this.isExistUserId(member.getUserid()))
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        memberDao.joinMember(member);
    }

    @Override
    public boolean isExistUserId(String userid) {
        return memberDao.isExistUserId(userid);
    }
}
