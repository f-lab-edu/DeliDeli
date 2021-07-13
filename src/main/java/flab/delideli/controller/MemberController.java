package flab.delideli.controller;

import flab.delideli.domain.MemberDTO;
import flab.delideli.service.MemberService;
import flab.delideli.util.error.StatusCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

import static flab.delideli.util.error.StatusCode.CONFLICT_USERID;
import static flab.delideli.util.error.StatusCode.OK_USERID;

@RestController
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    private final ResponseEntity<StatusCode> responseOK =
            new ResponseEntity<>(OK_USERID, HttpStatus.OK);
    private final ResponseEntity<StatusCode> responseConflict =
            new ResponseEntity<>(CONFLICT_USERID, HttpStatus.CONFLICT);

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

        if (idDuplicated) return responseConflict;

        return responseOK;

    }

}
