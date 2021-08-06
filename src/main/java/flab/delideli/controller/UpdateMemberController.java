package flab.delideli.controller;

import flab.delideli.dto.MemberDTO;
import flab.delideli.dto.UpdateDTO;
import flab.delideli.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UpdateMemberController {

    private MemberService memberService;

    private static final ResponseEntity acceptedResponseEntity = new ResponseEntity(HttpStatus.OK);

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity updateUser(@RequestBody UpdateDTO updateDTO) {
        memberService.updateUserInfo(updateDTO);
        return acceptedResponseEntity;
    }
}
