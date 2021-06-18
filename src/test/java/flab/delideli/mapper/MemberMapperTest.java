package flab.delideli.mapper;

import flab.delideli.dao.MemberDao;
import flab.delideli.dto.MemberDto;
import flab.delideli.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberMapperTest {

    @Autowired
    private MemberDao memberservice;

    @Test
    public void memberjoin() throws Exception{
        MemberDto member = new MemberDto("ddaad","ddda","ddddda","0101","sdad");

        memberservice.memberjoin(member);
    }
}
