package flab.delideli.controller;

import static flab.delideli.util.ResponseEntityCode.CONFLICT_RESPONSE_ENTITY;
import static flab.delideli.util.ResponseEntityCode.OK_RESPONSE_ENTITY;
import static flab.delideli.util.ResponseEntityCode.UNAUTHORIZED_RESPONSE_ENTITY;

import flab.delideli.dto.LoginDTO;
import flab.delideli.dto.MemberDTO;
import flab.delideli.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Api(tags = {"회원 컨트롤러 API"})
@AllArgsConstructor
public class MemberController {

    private MemberService memberService;
    private static final String USER_ID = "USER_ID";

    @RequestMapping(value ="/users", method = RequestMethod.POST)
    @ApiOperation(value = "회원 가입")
    public void joinMember(@RequestBody MemberDTO memberDto) {
        memberService.joinMember(memberDto);
    }

    @RequestMapping(value="/{userid}/duplicate",method = RequestMethod.POST)
    @ApiOperation(value = "회원 아이디 중복 체크")
    @ApiResponses({
        @ApiResponse(code = 200, message = "사용 가능한 아이디"),
        @ApiResponse(code = 409, message = "중복된 아이디")
    })
    public ResponseEntity<Void> checkUserId(@RequestBody String userid) {
        boolean result = memberService.isExistUserId(userid);
        if (!result) {
            return OK_RESPONSE_ENTITY;
        }
        return CONFLICT_RESPONSE_ENTITY;
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    @ApiOperation(value = "회원 로그인")
    @ApiResponses({
        @ApiResponse(code = 200, message = "로그인 성공"),
        @ApiResponse(code = 401, message = "로그인 실패")
    })
    public ResponseEntity loginUser(@RequestBody LoginDTO loginDTO, HttpSession session) {
        boolean result = memberService.isExistUserInfo(loginDTO);

        if(result) {
            session.setAttribute(USER_ID, loginDTO.getLoginid());
            return OK_RESPONSE_ENTITY;
        }
        else
            return UNAUTHORIZED_RESPONSE_ENTITY;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ApiOperation(value = "회원 로그아웃")
    public ResponseEntity logoutUser(HttpSession session) {
        session.removeAttribute(USER_ID);
        return OK_RESPONSE_ENTITY;
    }

}