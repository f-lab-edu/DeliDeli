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
    private static final ResponseEntity acceptedResponseEntity = new ResponseEntity(HttpStatus.OK);
    private static final ResponseEntity conflictResponseEntity = new ResponseEntity(HttpStatus.CONFLICT);
    private static final ResponseEntity unauthorizedResponseEntity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
    private static final String USER_ID = "USER_ID";

    @RequestMapping(value ="/users", method = RequestMethod.POST)
    public void joinMember(@RequestBody MemberDTO memberDto) {
        memberService.joinMember(memberDto);
    }

    @RequestMapping(value="/duplicated/{userid}",method = RequestMethod.POST)
    public ResponseEntity checkUserId(@RequestBody String userid) {
        boolean result = memberService.isExistUserId(userid);
        if (!result) {
            return acceptedResponseEntity;
        }
        return conflictResponseEntity;
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity loginUser(@RequestBody LoginDTO loginDTO, HttpSession session) {
        boolean result = memberService.isExistUserInfo(loginDTO);

        if(result) {
            session.setAttribute(USER_ID, loginDTO.getLoginid());
            return acceptedResponseEntity;
        }
        else
            return unauthorizedResponseEntity;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity logoutUser(HttpSession session) {
        session.removeAttribute(USER_ID);
        return acceptedResponseEntity;
    }
}