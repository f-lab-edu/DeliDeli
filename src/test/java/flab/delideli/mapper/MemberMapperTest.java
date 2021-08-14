package flab.delideli.mapper;

import flab.delideli.dto.MemberDTO;
import flab.delideli.dto.UpdateDTO;
import flab.delideli.encrypt.Encrypt;
import flab.delideli.encrypt.Encryption;
import flab.delideli.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberMapperTest {

    @Autowired
    private MemberService memberservice;
    private Encrypt encrypt;

    MemberDTO member1 = new MemberDTO("suykim", "suykima", "김나연", "010-2222-2222", "seong");
    MemberDTO member2 = new MemberDTO("jeong", "jenoga", "김연아", "010-7777-7777", "seoul");

    @Test
    public void memberjoin() throws Exception {
        memberservice.joinMember(member1);
        memberservice.joinMember(member2);
    }

    @Test
    public void isExistUserId() throws Exception {
        String userid1 = "ddddd"; //이미 있는 데이터
        String userid2 = "dddd"; //데이터베이스에 들어있지 않는 데이터
        Assertions.assertThat(memberservice.isExistUserId(userid1)).isTrue();
        Assertions.assertThat(memberservice.isExistUserId(userid2)).isFalse();
    }

    @Test
    public void update() throws Exception {
        UpdateDTO updateDTO1 = new UpdateDTO("","","");
        memberservice.updateUserInfo("jeong",updateDTO1);
    }

    @Test
    public void delete() throws Exception {
        memberservice.deleteUserInfo("suykim");
    }
}
