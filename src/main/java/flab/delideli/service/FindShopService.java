package flab.delideli.service;

import flab.delideli.dto.ShopDTO;
import flab.delideli.dto.ShoplistDTO;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

public interface FindShopService {

    List<ShoplistDTO> findAllShop(Integer cursor);
    List<ShoplistDTO> findbyShopName(String shopName, Integer cursor);
    ShopDTO getShop(int shopid);
}
