package flab.delideli.mapper;

import flab.delideli.dao.MemberDao;
import flab.delideli.dto.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberMapperTest {

    @Autowired
    private MemberDao memberService;

    @Test
    public void memberjoin() throws Exception{
        MemberDto member = new MemberDto();

        member.setUserid("daaaa");
        member.setPassword("lldaaal");
        member.setUsername("kimdaa");
        member.setPhone("0109992aa-22");
        member.setAddress("성남");
        memberService.memberjoin(member);
    }
}
