package flab.delideli.controller;

import flab.delideli.dto.ShopDTO;
import flab.delideli.dto.ShoplistDTO;
import flab.delideli.service.FindShopService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FindShopController {

    private FindShopService findShopService;

    @GetMapping(value = "/shops")
    public List<ShoplistDTO> findAllShop(@RequestParam(required = false) Integer lastId) {
        List<ShoplistDTO> shopDTOList = findShopService.findAllShop(lastId);
        return shopDTOList;
    }

    @PostMapping(value = "/shops/search")
    public List<ShoplistDTO> findbyShopName(@RequestParam(value = "shopname", required = false) String shopName, @RequestParam(value = "lastId", required = false) Integer lastId) {
        return findShopService.findbyShopName(shopName, lastId);
    }

    @GetMapping(value = "/shops/{shopId}")
    public ShopDTO getShop(@PathVariable int shopId) {
        ShopDTO shopDTO = findShopService.getShop(shopId);
        return shopDTO;
    }
}
