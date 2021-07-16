package flab.delideli.controller;

import flab.delideli.dto.MemberDTO;
import flab.delideli.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class MemberController {

    private MemberService memberService;
    private final ResponseEntity acceptedResponseEntity = new ResponseEntity(HttpStatus.ACCEPTED);
    private final ResponseEntity conflictResponseEntity = new ResponseEntity(HttpStatus.CONFLICT);

    @RequestMapping(value ="/users", method = RequestMethod.POST)
    public void joinMember(@RequestBody MemberDTO memberDto) {
        memberService.joinMember(memberDto);
    }

    @RequestMapping(value="/user/duplicateUserid",method = RequestMethod.POST)
    public ResponseEntity checkUserId(@RequestBody String userid) {
        boolean result = memberService.isExistUserId(userid);
        if (!result) { //아이디가 중복되지 않음
            return acceptedResponseEntity;
        }
        return conflictResponseEntity;
    }

}