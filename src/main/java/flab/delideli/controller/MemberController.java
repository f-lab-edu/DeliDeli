package flab.delideli.controller;

import flab.delideli.domain.MemberDTO;
import flab.delideli.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    private final ResponseEntity responseOK =
            new ResponseEntity(HttpStatus.OK);
    private final ResponseEntity responseConflict =
            new ResponseEntity(HttpStatus.CONFLICT);

    @RequestMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public void joinMember(@RequestBody @Valid MemberDTO memberDTO) {

        memberService.joinMember(memberDTO);

    }

    @RequestMapping("/{userId}/duplicate")
    public ResponseEntity userIdCheck(@RequestBody @PathVariable("userId") String userId) {

        boolean idDuplicated = memberService.isUserIdCheck(userId);

        if (idDuplicated) {
            return responseConflict;
        } else {
            return responseOK;
        }

    }

}
