package flab.delideli.service;

import flab.delideli.dao.ShopDao;
import flab.delideli.dto.ShopDTO;
import flab.delideli.exception.AlreadyAddedShopException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

	private final ShopDao shopDao;

	public ShopService(ShopDao shopDao) {
		this.shopDao = shopDao;
	}

	public void addShop(ShopDTO shopDTO) {

		boolean duplicatedShop = isDuplicatedShop(shopDTO.getShopName(), shopDTO.getOwnerId());

		if (duplicatedShop) {
			throw new AlreadyAddedShopException("해당 아이디로 이미 등록된 가게입니다.");
		}

		shopDao.insertShop(shopDTO);

	}

	public boolean isDuplicatedShop(String shopName, String ownerId) {
		return shopDao.isExistShop(shopName, ownerId);
	}

	public ShopDTO getMyShop(String shopName, String ownerId) {
		return shopDao.selectMyShop(shopName, ownerId);
	}

	public List<ShopDTO> getMyShopList(String ownerId) {
		return shopDao.selectMyShopList(ownerId);
	}

}
