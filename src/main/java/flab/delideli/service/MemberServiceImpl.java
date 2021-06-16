package flab.delideli.service;

import flab.delideli.dao.MemberDao;
import flab.delideli.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberServiceImpl implements MemberService{
    @Autowired
    MemberDao memberDao;

    @Override
    public void memberjoin(MemberDto member) throws Exception{
        memberDao.memberjoin(member);
    }
}
