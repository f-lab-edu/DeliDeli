package flab.delideli.controller;

import flab.delideli.annotation.CurrentUser;
import flab.delideli.annotation.UserAuthorization;
import flab.delideli.dto.MenuDTO;
import flab.delideli.dto.UpdateMenuDTO;
import flab.delideli.enums.UserLevel;
import flab.delideli.service.MenuService;
import flab.delideli.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	@UserAuthorization(role = UserLevel.OWNER)
	public void addMenu(@RequestBody @Valid MenuDTO menuDTO,
		@PathVariable long shopId, @CurrentUser String ownerId) {

		shopService.verifyShopOwner(shopId, ownerId);
		menuService.addMenu(menuDTO);

	}

	@PatchMapping
	@ApiOperation(value = "메뉴 수정")
	@UserAuthorization(role = UserLevel.OWNER)
	public void updateMenu(@RequestBody @Valid UpdateMenuDTO updateMenuDTO,
		@PathVariable long shopId, @CurrentUser String ownerId) {

		shopService.verifyShopOwner(shopId, ownerId);
		menuService.updateMenu(updateMenuDTO);

	}

	@DeleteMapping(value = "/{menuId}")
	@ApiOperation(value = "메뉴 삭제")
	@UserAuthorization(role = UserLevel.OWNER)
	public void deleteMenu(@RequestBody @PathVariable("menuId") long menuId,
		@PathVariable long shopId, @CurrentUser String ownerId) {

		shopService.verifyShopOwner(shopId, ownerId);
		menuService.deleteMenu(menuId);

	}

}