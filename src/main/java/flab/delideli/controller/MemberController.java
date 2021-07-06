package flab.delideli.controller;

import flab.delideli.domain.MemberDTO;
import flab.delideli.service.MemberService;
import flab.delideli.util.error.ErrorResponse;
import flab.delideli.util.error.StatusCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@AllArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    // 회원 가입
    @RequestMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public void joinMember(@RequestBody @Valid MemberDTO memberDTO)
            throws NoSuchAlgorithmException {

        memberService.joinMember(memberDTO);

    }

    // 사용자 아이디 중복 체크
    @RequestMapping("/idCheck/{userId}")
    public ResponseEntity<ErrorResponse> userIdCheck(@RequestBody @PathVariable("userId") String userId) {

        ResponseEntity<ErrorResponse> responseEntity = null;
        boolean idDuplicated = memberService.userIdCheck(userId);

        if (idDuplicated) {
            responseEntity = new ResponseEntity<>(ErrorResponse.of(StatusCode.CONFLICT_USERID), HttpStatus.CONFLICT);
        } else if (!idDuplicated) {
            responseEntity = new ResponseEntity<>(ErrorResponse.of(StatusCode.OK_USERID), HttpStatus.OK);
        }

        return responseEntity;

    }

}
