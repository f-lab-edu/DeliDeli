package flab.delideli.service;

import flab.delideli.dao.OwnerDao;
import flab.delideli.dto.OwnerDTO;
import flab.delideli.encrypt.Encryption;
import flab.delideli.exception.DuplicatedIdException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OwnerService {

	private final OwnerDao ownerDao;
	private final Encryption encryption;

	public void joinOwner(OwnerDTO owner) {

		boolean duplicatedOwnerId = isExistOwnerId(owner.getOwnerId());

		if (duplicatedOwnerId) {
			throw new DuplicatedIdException("이미 존재하는 아이디입니다.");
		}

		String encryptPassword = encryption.encrypt(owner.getOwnerPassword());
		OwnerDTO encryptOwner = new OwnerDTO(owner.getOwnerId(), encryptPassword,
			owner.getOwnerName(), owner.getOwnerPhone(), owner.getOwnerAddress());

		ownerDao.insertOwner(encryptOwner);

	}

	public boolean isExistOwnerId(String ownerId) {
		return ownerDao.isExistOwnerId(ownerId);
	}

	public String selectOwnerId(String ownerId) {
		return ownerDao.selectOwnerId(ownerId);
	}

	public void deleteOwner(String ownerId) {
		ownerDao.deleteOwner(ownerId);
	}

}
