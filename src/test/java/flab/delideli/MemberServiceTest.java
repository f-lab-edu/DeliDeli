package flab.delideli;

import flab.delideli.domain.MemberDTO;
import flab.delideli.service.MemberService;
import flab.delideli.util.encryption.EncryptionSHA256;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberServiceTest {

    private MemberDTO member1;
    private MemberDTO member2;
    private MemberDTO member3;

    private final MemberService memberService;

    private EncryptionSHA256 encryptionSHA256;

    @Autowired
    public MemberServiceTest(MemberService memberService) {
        this.memberService = memberService;
    }

    @BeforeEach
    public void beforeEach() {

        encryptionSHA256 = new EncryptionSHA256();

        member1 = new MemberDTO("syw", "yeol",
                "345ab", "010-1111-1111", "Busan");
        member2 = new MemberDTO("syw", "woo",
                "234ac", "010-1234-1234", "Daegu");
        member3 = new MemberDTO("sje", "jeong",
                "234ac", "010-2222-2222", "Seoul");

        memberService.initDB(memberService.getId(member1.getUserId()));
        memberService.initDB(memberService.getId(member2.getUserId()));
        memberService.initDB(memberService.getId(member3.getUserId()));

    }

    @Test
    public void 회원_가입() throws NoSuchAlgorithmException {


        memberService.joinMember(member1);
        Long id = memberService.getId(member1.getUserId());
        MemberDTO selectMember = memberService.selectMember(id);

        System.out.println(memberService.selectMember(id));

    }

    @Test
    public void 중복_아이디_검사() throws NoSuchAlgorithmException, IllegalStateException {

        memberService.joinMember(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
        ()->memberService.joinMember(member2));

        assertEquals(e.getMessage(), "이미 가입된 아이디입니다.");

    }

    @Test
    public void 비밀번호_암호화_테스트() throws NoSuchAlgorithmException {

        // member2와 member3는 같은 비밀번호를 사용한다.
        String userPassword1 = member2.getUserPassword();
        String userPassword2 = member3.getUserPassword();

        // 비밀번호 암호화에 사용할 솔트 값을 각각 생성한다.
        String salt1 = encryptionSHA256.getSalt();
        String salt2 = encryptionSHA256.getSalt();

        // 해싱을 수행하여 비밀번호를 암호화한다.
        String hashPassword1 = encryptionSHA256.getHashing(userPassword1, salt1);
        String hashPassword2 = encryptionSHA256.getHashing(userPassword2, salt2);

        // 두 값이 다르다면 같은 비밀번호를 사용하더라도 해시값은 다르다는 것을 의미한다.
        assertThat(hashPassword1).isNotEqualTo(hashPassword2);

    }

    @Test
    public void 비밀번호_암호화_DB저장_테스트() throws NoSuchAlgorithmException {

        // member1의 비밀번호 원본 값을 userPassword에 할당한다.
        String userPassword = member1.getUserPassword();

        // 회원 가입을 진행하는 과정에서 비밀번호가 암호화된다.
        memberService.joinMember(member1);

        // DB에 저장된 비밀번호 값을 hashPassword에 할당한다.
        MemberDTO selectMember = memberService.selectMember(memberService.getId(member1.getUserId()));
        String hashPassword = selectMember.getUserPassword();

        // 두 값이 다르다면 DB에 암호화된 비밀번호가 저장되었음을 의미한다.
        assertThat(userPassword).isNotEqualTo(hashPassword);

    }

}
