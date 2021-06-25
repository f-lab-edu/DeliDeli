package flab.delideli.controller;

import flab.delideli.dto.MemberDto;
import flab.delideli.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value ="/signup", method = RequestMethod.POST)
    public void joinMember(@RequestBody MemberDto memberDto) {
        memberService.joinMember(memberDto);
    }

    @RequestMapping(value="/signup/check",method = RequestMethod.POST)
    public int checkUserId(@RequestBody String userid) {
        boolean result = memberService.isExistUserId(userid);
        if (!result) { //아이디가 중복되지 않음
            return 0;
        }
        return 1; //아이디가 중복됨
    }
}