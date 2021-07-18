package flab.delideli.controller;

import flab.delideli.dto.LoginDTO;
import flab.delideli.dto.MemberDTO;
import flab.delideli.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity loginUser(@RequestBody LoginDTO loginDTO, HttpSession session) {
        boolean result = memberService.login(loginDTO);
        if(result == true) {//아이디와 비밀번호 있음
            //세션아이디 이미 존재하는 지 확인후 세션에 값 저장하기
            if(memberService.isExistUserSession(loginDTO.getLoginid()) == true) {
                session.setAttribute("sessionid", memberService.getSessionId(loginDTO.getLoginid()));
                return acceptedResponseEntity;
            }
            else {
                session.setAttribute("sessionid", memberService.createSessionId(loginDTO.getLoginid()));
                return acceptedResponseEntity;
            }
        }
        else
            return conflictResponseEntity;
    }
}