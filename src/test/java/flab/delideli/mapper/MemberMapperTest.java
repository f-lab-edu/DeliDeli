package flab.delideli.mapper;

import flab.delideli.dto.MemberDTO;
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
        MemberDTO member = new MemberDTO("ddddd","dda","ddddda","0101","sdad");
        memberservice.joinMember(member);
    }

    @Test
    public void isExistUserId() throws Exception {
        String userid1 = "ddaad"; //이미 있는 데이터
        String userid2 = "susu"; //데이터베이스에 들어있지 않는 데이터
        Assertions.assertThat(memberservice.isExistUserId(userid1)).isTrue();
        Assertions.assertThat(memberservice.isExistUserId(userid2)).isFalse();
    }

}
