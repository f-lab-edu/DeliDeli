package flab.delideli.controller;

import flab.delideli.annotation.LoginUserLevel;
import flab.delideli.dto.MenuDTO;
import flab.delideli.enums.UserLevel;
import flab.delideli.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"메뉴 컨트롤러 API"})
@AllArgsConstructor
@RequestMapping("/shops/menus")
public class MenuController {

	private MenuService menuService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "메뉴 등록")
	@LoginUserLevel(role = UserLevel.OWNER_LEVEL)
	public void addMenu(@RequestBody @Valid MenuDTO menuDTO) {
		menuService.addMenu(menuDTO);
	}

}
