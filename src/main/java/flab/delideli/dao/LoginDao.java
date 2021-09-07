package flab.delideli.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginDao {

	boolean isExistUserInfo(@Param("loginId") String loginId,
		@Param("loginPassword") String loginPassword);

}
