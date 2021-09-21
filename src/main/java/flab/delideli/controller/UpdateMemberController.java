package flab.delideli.controller;

import flab.delideli.dto.UpdateDTO;
import flab.delideli.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@AllArgsConstructor
public class UpdateMemberController {

    private MemberService memberService;

    private static final ResponseEntity OK_RESPONSE_ENTITY = new ResponseEntity(HttpStatus.OK);
    private static final String USER_ID = "USER_ID";

    @PatchMapping(value = "/{userId}")
    public ResponseEntity updateUser(HttpSession session, @RequestBody UpdateDTO updateDTO) {
        String currentUserId = (String) session.getAttribute(USER_ID);
        memberService.updateUserInfo(currentUserId ,updateDTO);
        return OK_RESPONSE_ENTITY;
    }

    @DeleteMapping(value="/{userId}")
    public ResponseEntity deleteUser(HttpSession session) {
        String currentUserId = (String) session.getAttribute(USER_ID);
        memberService.deleteUserInfo(currentUserId);
        session.invalidate();
        return OK_RESPONSE_ENTITY;
    }

}