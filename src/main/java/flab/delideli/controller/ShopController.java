package flab.delideli.controller;


import flab.delideli.dto.ShopDTO;
import flab.delideli.annotation.LoginUserLevel;
import flab.delideli.enums.UserLevel;
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
@RequestMapping("/owners/shops")
public class ShopController {

	private ShopService shopService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "가게 등록")
	@LoginUserLevel(role = UserLevel.OWNER_LEVEL)
	public void addShop(@RequestBody @Valid ShopDTO shopDTO) {
		shopService.addShop(shopDTO);
	}

	@GetMapping
	@ApiOperation(value = "가게 이름과 사장님 ID로 가게 조회")
	@LoginUserLevel(role = UserLevel.OWNER_LEVEL)
	public ShopDTO getShop(
			@RequestParam(value = "name", required = false, defaultValue = "shop") String shopName,
			@RequestParam(value = "owner", required = false, defaultValue = "owner") String ownerId) {
		return shopService.getShop(shopName, ownerId);
	}

	@GetMapping(value = "/{ownerId}")
	@ApiOperation(value = "사장님 ID로 가게 리스트 조회")
	@LoginUserLevel(role = UserLevel.OWNER_LEVEL)
	public List<ShopDTO> getShopList(
			@PathVariable("ownerId") String ownerId) {
		return shopService.getShopList(ownerId);
	}
}
