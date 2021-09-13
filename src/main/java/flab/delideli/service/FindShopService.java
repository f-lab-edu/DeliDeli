package flab.delideli.service;

import flab.delideli.dto.ShopDTO;
import flab.delideli.dto.ShoplistDTO;

import java.util.List;

public interface FindShopService {

    List<ShoplistDTO> findAllShop(int pageNumber);
}
