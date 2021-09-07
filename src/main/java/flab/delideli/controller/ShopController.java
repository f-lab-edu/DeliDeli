package flab.delideli.controller;

import flab.delideli.dto.ShopDTO;
import flab.delideli.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"가게 컨트롤러 API"})
@AllArgsConstructor
@RequestMapping("/shops")
public class ShopController {

	private ShopService shopService;

	@PostMapping(value = "/add")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "가게 등록")
	public void addShop(@RequestBody @Valid ShopDTO shopDTO) {
		shopService.addShop(shopDTO);
	}

	@PostMapping(value = "/{shopName}")
	@ApiOperation(value = "가게 이름으로 사장님 가게 조회")
	public ResponseEntity<ShopDTO> getMyShop(
		@PathVariable("shopName") String shopName, String ownerId) {

		ShopDTO myShop = shopService.getMyShop(shopName, ownerId);

		return ResponseEntity.ok(myShop);

	}

	@PostMapping(value = "/list")
	@ApiOperation(value = "사장님 가게 리스트 조회")
	public ResponseEntity<List<ShopDTO>> getMyShopList(String ownerId) {

		List<ShopDTO> myShopList = shopService.getMyShopList(ownerId);

		return ResponseEntity.ok(myShopList);

	}

}
