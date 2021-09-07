package flab.delideli.controller;

import flab.delideli.annotation.LoginUserLevel;
import flab.delideli.annotation.LoginUserLevel.UserLevel;
import flab.delideli.dto.ShopDTO;
import flab.delideli.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"가게 컨트롤러 API"})
@AllArgsConstructor
@RequestMapping("/shops")
public class ShopController {

	private ShopService shopService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "가게 등록")
	@LoginUserLevel(UserLevel= UserLevel.OWNER_LEVEL)
	public void addShop(@RequestBody @Valid ShopDTO shopDTO) {
		shopService.addShop(shopDTO);
	}

	@GetMapping
	@ApiOperation(value = "가게 이름과 사장님 ID로 가게 조회")
	@LoginUserLevel(UserLevel= UserLevel.OWNER_LEVEL)
	public ResponseEntity<ShopDTO> getShop(
		@RequestParam(value = "name") String shopName,
		@RequestParam(value = "owner") String ownerId) {

		ShopDTO myShop = shopService.getShop(shopName, ownerId);

		return ResponseEntity.ok(myShop);

	}

	@GetMapping(value = "/{ownerId}")
	@ApiOperation(value = "사장님 ID로 가게 리스트 조회")
	@LoginUserLevel(UserLevel= UserLevel.OWNER_LEVEL)
	public ResponseEntity<List<ShopDTO>> getShopList(
		@PathVariable("ownerId") String ownerId) {

		List<ShopDTO> myShopList = shopService.getShopList(ownerId);

		return ResponseEntity.ok(myShopList);

	}

}
