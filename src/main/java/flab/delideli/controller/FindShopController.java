package flab.delideli.controller;

import flab.delideli.dto.ShopDTO;
import flab.delideli.service.FindShopService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FindShopController{

    private FindShopService findShopService;

    @GetMapping(value="/shops")
    public ResponseEntity<List<ShopDTO>> findAllShop() {
        List<ShopDTO> shopDTOList = findShopService.findAllShop();
        return new ResponseEntity<>(shopDTOList,HttpStatus.OK);
    }
}
