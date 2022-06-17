package flab.delideli.controller;

import flab.delideli.dto.LoginDTO;
import flab.delideli.dto.MemberDTO;
import flab.delideli.service.LoginService;
import flab.delideli.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"회원 컨트롤러 API"})
@AllArgsConstructor
@RequestMapping("/users")
public class MemberController {

	private final MemberService memberService;
	private final LoginService loginService;

	@PostMapping(value = "/join")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "회원 가입")
	public void joinMember(@RequestBody @Valid MemberDTO member) {
		memberService.joinMember(member);
	}

	@PostMapping(value = "/{userId}/duplicate")
	@ApiOperation(value = "회원 아이디 중복 체크")
	@ApiResponses({
			@ApiResponse(code = 200, message = "사용 가능한 아이디"),
			@ApiResponse(code = 409, message = "중복된 아이디")
	})
	public void duplicatedUserIdCheck(
			@RequestBody @PathVariable("userId") String userId) {
		memberService.duplicatedId(userId);
	}

	@PostMapping(value = "/login")
	@ApiOperation(value = "회원 로그인")
	@ApiResponses({
			@ApiResponse(code = 200, message = "로그인 성공"),
			@ApiResponse(code = 401, message = "로그인 실패")
	})
	public LoginDTO loginUser(@RequestBody LoginDTO loginDTO) {
		loginService.login(loginDTO);
		return loginDTO;
	}

	@DeleteMapping(value = "/logout")
	@ApiOperation(value = "회원 로그아웃")
	public void logoutUser() {
		loginService.logout();
	}
}