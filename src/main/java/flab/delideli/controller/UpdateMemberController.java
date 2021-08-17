package flab.delideli.controller;

import flab.delideli.dto.MemberDTO;
import flab.delideli.dto.UpdateDTO;
import flab.delideli.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@AllArgsConstructor
public class UpdateMemberController {


    private MemberService memberService;

    private static final ResponseEntity OK_RESPONSE_ENTITY = new ResponseEntity(HttpStatus.OK);
    private static final ResponseEntity UNAUTHORIZED_RESPONSE_ENTITY = new ResponseEntity(HttpStatus.UNAUTHORIZED);
    private static final String USER_ID = "USER_ID";

    @RequestMapping(value = "/{userId}", method = RequestMethod.PATCH)
    public ResponseEntity updateUser(HttpSession session, @RequestBody UpdateDTO updateDTO) {
        String currentUserId = (String) session.getAttribute(USER_ID);
        if (currentUserId == null) {
            return UNAUTHORIZED_RESPONSE_ENTITY;
        }
        memberService.updateUserInfo(currentUserId ,updateDTO);
        return OK_RESPONSE_ENTITY;
    }

    @RequestMapping(value="/{userId}/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(HttpSession session) {
        String currentUserId = (String) session.getAttribute(USER_ID);
        if (currentUserId == null)
            return UNAUTHORIZED_RESPONSE_ENTITY;
        memberService.deleteUserInfo(currentUserId);
        session.invalidate();
        return OK_RESPONSE_ENTITY;
    }
}
