package flab.delideli.controller;

import flab.delideli.dto.MemberDto;
import flab.delideli.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class MemberController {

    private MemberService memberService;

    @RequestMapping(value ="/signup", method = RequestMethod.POST)
    public void joinMember(@RequestBody MemberDto memberDto) {
        memberService.joinMember(memberDto);
    }

    @RequestMapping(value="/signup/checkUserid",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity checkUserId(@RequestBody String userid) {
        boolean result = memberService.isExistUserId(userid);
        if (!result) { //아이디가 중복되지 않음
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }
}