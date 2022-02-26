package flab.delideli.dao;

import flab.delideli.dto.AddCartDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CartDao {

    void insertCart(@Param("addCartDTO") AddCartDTO addCartDTO, @Param("userid") String userId);
}
