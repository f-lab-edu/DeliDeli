package flab.delideli.controller;

import flab.delideli.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"관리자의 사장님 계정 관리 컨트롤러 API"})
@AllArgsConstructor
@RequestMapping("/admins/owners")
public class AdminController {

	private final MemberService memberService;

	@PutMapping(value = "/{ownerId}/submission")
	@ApiOperation(value = "사장님 서류 제출 시 제출 완료로 업데이트")
	@LoginUserLevel(role = UserLevel.ADMIN_LEVEL)
	public void setOwnerDocsSubmitted(
		@PathVariable("ownerId") String ownerId) {
		memberService.setOwnerDocsSubmitted(ownerId);
	}

	@PutMapping(value = "/{ownerId}/approval")
	@ApiOperation(value = "사장님 서류 승인 시 로그인 승인으로 업데이트")
	@LoginUserLevel(role = UserLevel.ADMIN_LEVEL)
	public void setOwnerDocsApproved(
		@PathVariable("ownerId") String ownerId) {
		memberService.setOwnerDocsApproved(ownerId);
		memberService.setOwnerLoginApproval(ownerId);
	}

}