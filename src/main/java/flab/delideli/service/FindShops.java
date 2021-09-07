package flab.delideli.service;

import flab.delideli.dao.FindShopDao;
import flab.delideli.dao.ShopDao;
import flab.delideli.dto.ShopDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindShops implements FindShopService{

    private FindShopDao findShopDao;

    @Override
    public List<ShopDTO> findAllShop() {
        return findShopDao.findAllShop();
    }
}
