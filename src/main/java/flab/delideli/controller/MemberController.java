package flab.delideli.controller;

import flab.delideli.domain.MemberDTO;
import flab.delideli.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 가입 완료 후 로그인 창으로 이동
    @RequestMapping(value = "/join")
    public String joinMember(@Valid @RequestBody MemberDTO memberDTO)
            throws NoSuchAlgorithmException {

        memberService.joinMember(memberDTO);

        return "redirect:/login";

    }

    // 사용자 아이디 중복 체크
    @RequestMapping(value = "/join/idCheck/{userId}")
    public ResponseEntity<String> userIdCheck(@PathVariable("userId") String userId) {

        ResponseEntity<String> responseEntity = null;
        int idDuplicated = memberService.userIdCheck(userId);

        if (idDuplicated == 1) {
            responseEntity = new ResponseEntity<>("중복된 아이디입니다.", HttpStatus.CONFLICT);
        } else if (idDuplicated == 0) {
            responseEntity = new ResponseEntity<>("사용 가능한 아이디입니다.", HttpStatus.OK);
        }

        return responseEntity;

    }

}
