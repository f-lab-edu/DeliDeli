package flab.delideli.service;

import flab.delideli.dao.ShopDao;
import flab.delideli.dto.ShopDTO;
import flab.delideli.exception.AlreadyAddedValueException;
import flab.delideli.exception.UnauthorizedException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

@Service
@RequiredArgsConstructor
public class ShopService {

	private final ShopDao shopDao;

	public void addShop(ShopDTO shopDTO) {

		boolean duplicatedShop = isDuplicatedShop(shopDTO.getShopName(), shopDTO.getOwnerId());

		if (duplicatedShop) {
			throw new AlreadyAddedValueException("해당 아이디로 이미 등록된 가게입니다.");
		}

		shopDao.insertShop(shopDTO);

	}

	public boolean isDuplicatedShop(String shopName, String ownerId) {
		return shopDao.isExistShop(shopName, ownerId);
	}

	public ShopDTO getShopByNameAndOwnerId(String shopName, String ownerId) {
		return shopDao.selectShopByNameAndOwnerId(shopName, ownerId);
	}

	public List<ShopDTO> getShopListByOwnerId(String ownerId) {
		return shopDao.selectShopListByOwnerId(ownerId);
	}

	public void verifyShopOwner(Long id, String ownerId) {

		boolean isCurrentUserMatchingOwnerId =
			shopDao.isCurrentUserMatchingOwnerId(id, ownerId);

		if(!isCurrentUserMatchingOwnerId) {
			throw new UnauthorizedException("이 가게의 사장님만 접근 가능합니다.");
		}

	}

}
