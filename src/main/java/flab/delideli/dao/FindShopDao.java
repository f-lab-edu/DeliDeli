package flab.delideli.dao;

import flab.delideli.dto.ShopDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FindShopDao {

    List<ShopDTO> findAllShop();
}
