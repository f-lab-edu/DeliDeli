package flab.delideli.controller;


import flab.delideli.annotation.CurrentUser;
import flab.delideli.dto.ShopDTO;
import flab.delideli.annotation.UserAuthorization;
import flab.delideli.enums.UserLevel;
import flab.delideli.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@UserAuthorization(role = UserLevel.OWNER)
	public void addShop(@RequestBody @Valid ShopDTO shopDTO) {
		shopService.addShop(shopDTO);
	}

	@GetMapping(value = "/{shopId}")
	@ApiOperation(value = "shopId로 가게 조회")
	@UserAuthorization(role = UserLevel.OWNER)
	public ShopDTO getShop(@PathVariable long shopId, @CurrentUser String ownerId) {
		return shopService.getShopByShopIdAndOwnerId(shopId, ownerId);
	}

	@GetMapping(value = "/{ownerId}")
	@ApiOperation(value = "사장님 ID로 가게 리스트 조회")
	@UserAuthorization(role = UserLevel.OWNER)
	public List<ShopDTO> getShopList(
			@PathVariable("ownerId") String ownerId) {
		return shopService.getShopListByOwnerId(ownerId);
	}

}
