package flab.delideli.service;

import flab.delideli.dao.ShopDao;
import flab.delideli.dto.ShopDTO;
import flab.delideli.exception.AlreadyAddedValueException;
import flab.delideli.exception.UnauthorizedException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {

	private final ShopDao shopDao;

	public void addShop(ShopDTO shopDTO) {

		boolean duplicatedShop = isDuplicatedShopName(shopDTO.getShopName(), shopDTO.getShopLocation());

		if (duplicatedShop) {
			throw new AlreadyAddedValueException("해당 아이디로 이미 등록된 가게입니다.");
		}

		shopDao.insertShop(shopDTO);

	}

	private boolean isDuplicatedShopName(String shopName, String shopLocation) {
		return shopDao.isExistShop(shopName, shopLocation);
	}

	public ShopDTO getShopByShopIdAndOwnerId(long shopId, String ownerId) {
		validateShopOwner(shopId, ownerId);
		return shopDao.selectShopByShopIdAndOwnerId(shopId, ownerId);
	}

	private void validateShopOwner(long shopId, String ownerId) {
		if(!ownerId.equals(shopDao.getOwnerIdInShops(shopId))) {
			throw new IllegalArgumentException("잘못된 값입니다");
		}
	}

	public List<ShopDTO> getShopListByOwnerId(String ownerId) {
		return shopDao.selectShopListByOwnerId(ownerId);
	}

	public void verifyShopOwner(Long shopId, String ownerId) {

		boolean isCurrentUserMatchingOwnerId =
			shopDao.isCurrentUserMatchingOwnerId(shopId, ownerId);

		if(!isCurrentUserMatchingOwnerId) {
			throw new UnauthorizedException("이 가게의 사장님만 접근 가능합니다.");
		}

	}


}
