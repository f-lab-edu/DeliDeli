package flab.delideli.service;

import flab.delideli.dao.FindShopDao;
import flab.delideli.dto.ShopDTO;
import flab.delideli.dto.ShoplistDTO;
import flab.delideli.paging.PagingCriteria;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindShops implements FindShopService {

    private FindShopDao findShopDao;

    @Override
    @Cacheable(value ="findAllshop", key = "#cursor", unless="#cursor == null")
    public List<ShoplistDTO> findAllShop(Integer cursor) {
        PagingCriteria pagingCriteria = new PagingCriteria(cursor);
        return findShopDao.findAllShop(pagingCriteria);
    }

    @Override
    @Cacheable(value = "findbyShopName", key = "{#shopName, #cursor}")
    public List<ShoplistDTO> findbyShopName(String shopName, Integer cursor) {
        PagingCriteria pagingCriteria = new PagingCriteria(cursor);
        return findShopDao.findShopName(shopName, pagingCriteria);
    }

    @Override
    public ShopDTO getShop(int shopid) {
        return findShopDao.getShop(shopid);
    }
}
