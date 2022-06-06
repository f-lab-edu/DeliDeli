package flab.delideli.controller;

import flab.delideli.annotation.UserAuthorization;
import flab.delideli.enums.UserLevel;
import flab.delideli.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"관리자의 관리 컨트롤러 API"})
@AllArgsConstructor
@RequestMapping("/admins")
public class AdminController {

	private final MemberService memberService;

	@PutMapping(value = "/owners/{ownerId}/submission")
	@ApiOperation(value = "사장님 서류 제출 시 제출 완료로 업데이트")
	@UserAuthorization(role = UserLevel.ADMIN)
	public void setOwnerDocsSubmission(
		@PathVariable("ownerId") String ownerId) {
		memberService.setOwnerDocsSubmission(ownerId);
	}

	@PutMapping(value = "/owners/{ownerId}/approval")
	@ApiOperation(value = "사장님 서류 통과 시 로그인 승인으로 업데이트")
	@UserAuthorization(role = UserLevel.ADMIN)
	public void setOwnerLoginApproval(
		@PathVariable("ownerId") String ownerId) {
		memberService.setOwnerLoginApproval(ownerId);
	}

}