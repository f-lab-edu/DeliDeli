package flab.delideli.controller;

import flab.delideli.dto.LoginDTO;
import flab.delideli.dto.OwnerDTO;
import flab.delideli.service.OwnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"사장님 컨트롤러 API"})
@AllArgsConstructor
@RequestMapping("/owners")
public class OwnerController {

	private OwnerService ownerService;

	private static final ResponseEntity<Void> OK_RESPONSE_ENTITY =
		new ResponseEntity<>(HttpStatus.OK);
	private static final ResponseEntity<Void> CONFLICT_RESPONSE_ENTITY =
		new ResponseEntity<>(HttpStatus.CONFLICT);
	private static final String OWNER_ID = "OWNER_ID";

	@PostMapping(value = "/join")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "사장님 회원 가입")
	public void joinOwner(@RequestBody @Valid OwnerDTO ownerDTO) {
		ownerService.joinOwner(ownerDTO);
	}

	@PostMapping(value = "/{ownerId}/duplicate")
	@ApiOperation(value = "사장님 아이디 중복 체크")
	@ApiResponses({
		@ApiResponse(code = 200, message = "사용 가능한 아이디"),
		@ApiResponse(code = 409, message = "중복된 아이디")
	})
	public ResponseEntity<Void> duplicatedOwnerIdCheck(
		@RequestBody @PathVariable("ownerId") String ownerId) {

		boolean idDuplicated = ownerService.isExistOwnerId(ownerId);

		if (idDuplicated) {
			return CONFLICT_RESPONSE_ENTITY;
		}
			return OK_RESPONSE_ENTITY;

	}

	@PostMapping(value = "/login")
	@ResponseStatus
	@ApiOperation(value = "사장님 로그인")
	@ApiResponses({
		@ApiResponse(code = 200, message = "로그인 성공"),
		@ApiResponse(code = 401, message = "로그인 실패")
	})
	public void loginOwner(@RequestBody LoginDTO loginDTO, HttpSession session) {

		ownerService.loginOwner(loginDTO);
		session.setAttribute(OWNER_ID, loginDTO.getLoginid());

	}

	@RequestMapping(value = "/logout", method = RequestMethod.DELETE)
	@ResponseStatus
	@ApiOperation(value = "사장님 로그아웃")
	public void logoutOwner(HttpSession session) {
		session.removeAttribute(OWNER_ID);
	}

}
