package flab.delideli.controller;

import flab.delideli.dto.ShopDTO;
import flab.delideli.dto.ShoplistDTO;
import flab.delideli.service.FindShopService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FindShopController{

    private FindShopService findShopService;

    @GetMapping(value="/shops/{pageNumber}")
    public List<ShoplistDTO> findAllShop(@PathVariable int pageNumber) {
        List<ShoplistDTO> shopDTOList = findShopService.findAllShop(pageNumber);
        return shopDTOList;
    }
}
