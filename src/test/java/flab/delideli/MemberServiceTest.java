package flab.delideli;

import flab.delideli.domain.MemberDTO;
import flab.delideli.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MemberServiceTest {

    private MemberService memberService;

    @Autowired
    public MemberServiceTest(MemberService memberService) {
        this.memberService = memberService;
    }

//    @AfterEach
//    public void afterEach() {
//
//        memberService.initMember();
//
//    }

    @Test
    public void 회원_가입() {

        MemberDTO member = new MemberDTO("sjeasfggdf", "seong",
                "1234a", "010-2222-2222", "Seoul");

        memberService.joinMember(member);
        MemberDTO selectMember = memberService.selectMember(1L);

        assertThat(member.getUserId()).isEqualTo(selectMember.getUserId());

    }

    @Test
    public void 중복_아이디_검사() throws Exception {

        MemberDTO member1 = new MemberDTO("syw", "yeol",
                "345ab", "010-1111-1111", "Busan");
        MemberDTO member2 = new MemberDTO("syw", "woo",
                "234ac", "010-1234-1234", "Daegu");

        memberService.joinMember(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
        ()->memberService.joinMember(member2));

        assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");

    }

}
