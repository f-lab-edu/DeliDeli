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
@AllArgsConstructor
public class UpdateMemberController {


    private MemberService memberService;

    private static final ResponseEntity OKResponseEntity = new ResponseEntity(HttpStatus.OK);
    private static final ResponseEntity unauthorizedResponseEntity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
    private static final String USER_ID = "USER_ID";

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity updateUser(HttpSession session, @RequestBody UpdateDTO updateDTO) {
        String currentUserId = (String) session.getAttribute(USER_ID);
        if (currentUserId == null) {
            return unauthorizedResponseEntity;
        }
        memberService.updateUserInfo(currentUserId ,updateDTO);
        return OKResponseEntity;
    }
}
