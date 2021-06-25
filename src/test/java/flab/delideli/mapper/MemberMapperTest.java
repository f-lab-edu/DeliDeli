package flab.delideli.mapper;

import flab.delideli.dto.MemberDto;
import flab.delideli.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberMapperTest {

    @Autowired
    private MemberService memberservice;

    @Test
    public void memberjoin() throws Exception{
        MemberDto member = new MemberDto("ddaad","ddda","ddddda","0101","sdad");
        memberservice.joinMember(member);
    }

    @Test
    public void isExistUserId() throws Exception {
        String userid1 = "ddaad";
        String userid2 = "susu";
        Assertions.assertThat(memberservice.isExistUserId(userid1)).isTrue();
        Assertions.assertThat(memberservice.isExistUserId(userid2)).isFalse();
    }
}
