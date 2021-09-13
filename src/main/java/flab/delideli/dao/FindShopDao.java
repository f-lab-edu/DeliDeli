package flab.delideli.dao;

import flab.delideli.dto.ShopDTO;
import flab.delideli.dto.ShoplistDTO;
import flab.delideli.paging.PagingCriteria;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FindShopDao {

    List<ShoplistDTO> findAllShop(PagingCriteria pagingCriteria);
}
