package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.annotation.LoginUserLevel;
import flab.delideli.dto.MenuDTO;
import flab.delideli.enums.UserLevel;
import flab.delideli.service.MenuService;
import flab.delideli.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"메뉴 컨트롤러 API"})
@AllArgsConstructor
@RequestMapping("/shops/{shopId}/menus")
public class MenuController {

	private MenuService menuService;
	private ShopService shopService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "메뉴 등록")
	@LoginUserLevel(role = UserLevel.OWNER_LEVEL)
	public void addMenu(@RequestBody @Valid MenuDTO menuDTO,
		@PathVariable Long shopId, @CurrentUser String ownerId) {

		shopService.verifyShopOwner(shopId, ownerId);
		menuService.addMenu(menuDTO);

	}

}