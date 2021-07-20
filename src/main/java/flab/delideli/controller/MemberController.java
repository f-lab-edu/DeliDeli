package flab.delideli.controller;

import flab.delideli.domain.RequestLoginDTO;
import flab.delideli.domain.MemberDTO;
import flab.delideli.service.MemberService;
import flab.delideli.util.error.StatusCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

import static flab.delideli.util.error.StatusCode.*;

@RestController
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    private final ResponseEntity responseOK =
            new ResponseEntity(HttpStatus.OK);
    private final ResponseEntity<StatusCode> responseConflict =
            new ResponseEntity<>(CONFLICT_USERID, HttpStatus.CONFLICT);
    private final ResponseEntity responseUnauthorized =
            new ResponseEntity(HttpStatus.UNAUTHORIZED);

    // 회원 가입
    @RequestMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public void joinMember(@RequestBody @Valid MemberDTO memberDTO)
            throws NoSuchAlgorithmException {

        memberService.joinMember(memberDTO);

    }

    // 사용자 아이디 중복 체크
    @RequestMapping("/{userId}/checkId")
    public ResponseEntity<StatusCode> userIdCheck(@RequestBody @PathVariable("userId") String userId) {

        boolean idDuplicated = memberService.userIdCheck(userId);

        if (idDuplicated) { return responseConflict; }

        return responseOK;

    }

    // 로그인
    @RequestMapping("/login")
    public ResponseEntity login(@RequestBody @Valid RequestLoginDTO loginDTO, HttpSession session) {

        boolean loginCheck = memberService.loginCheck(loginDTO);

        if (!loginCheck) { return responseUnauthorized; }

        memberService.login(session, loginDTO.getUserId());

        return responseOK;

    }

    @RequestMapping("/logout")
    public ResponseEntity logout(HttpSession session) {

        memberService.logout(session);

        return responseOK;

    }

}
