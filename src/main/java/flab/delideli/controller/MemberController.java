package flab.delideli.controller;

import flab.delideli.domain.RequestLoginDTO;
import flab.delideli.domain.MemberDTO;
import flab.delideli.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    private final ResponseEntity<Void> responseOK =
            new ResponseEntity<>(HttpStatus.OK);
    private final ResponseEntity<Void> responseConflict =
            new ResponseEntity<>(HttpStatus.CONFLICT);
    private final ResponseEntity<Void> responseUnauthorized =
            new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    @RequestMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public void joinMember(@RequestBody @Valid MemberDTO memberDTO) {

        memberService.joinMember(memberDTO);

    }

    @RequestMapping("/{userId}/duplicate")
    public ResponseEntity<Void> duplicatedUserIdCheck(@RequestBody @PathVariable("userId") String userId) {

        boolean idDuplicated = memberService.isDuplicatedUserId(userId);

        if (idDuplicated) {
            return responseConflict;
        } else {
            return responseOK;
        }

    }

    @RequestMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid RequestLoginDTO loginDTO, HttpSession session) {

        boolean loginCheck = memberService.isLoginCheck(loginDTO);
        String userId = loginDTO.getUserId();
        Long id = memberService.getId(userId);

        if (!loginCheck) {
            return responseUnauthorized;
        }

        memberService.login(session, id);

        return responseOK;

    }

    @RequestMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {

        memberService.logout(session);
      
        return responseOK;

    }