package flab.delideli.controller;

import flab.delideli.dto.MemberDTO;
import flab.delideli.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@AllArgsConstructor
public class MemberController {

    private MemberService memberService;
    private final ResponseEntity acceptedResponseEntity = new ResponseEntity(HttpStatus.ACCEPTED);
    private final ResponseEntity conflictResponseEntity = new ResponseEntity(HttpStatus.CONFLICT);

    @RequestMapping(value ="/signup", method = RequestMethod.POST)
    public void joinMember(@RequestBody MemberDTO memberDto) throws NoSuchAlgorithmException {
        memberService.joinMember(memberDto);
    }

    @RequestMapping(value="/signup/checkUserid",method = RequestMethod.POST)
    public ResponseEntity checkUserId(@RequestBody String userid) {
        boolean result = memberService.isExistUserId(userid);
        if (!result) { //아이디가 중복되지 않음
            return acceptedResponseEntity;
        }
        return conflictResponseEntity;
    }

}