package flab.delideli.dao;

import flab.delideli.dto.OwnerDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OwnerDao {

	void insertOwner(OwnerDTO owner);

	boolean isExistOwnerId(@Param("ownerId") String ownerId);

}
