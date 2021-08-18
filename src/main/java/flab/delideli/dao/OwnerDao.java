package flab.delideli.dao;

import flab.delideli.dto.OwnerDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerDao {

	void insertOwner(OwnerDTO owner);

	boolean isExistOwnerId(String ownerId);

}
