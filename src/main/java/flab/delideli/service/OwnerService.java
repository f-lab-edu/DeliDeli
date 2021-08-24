package flab.delideli.service;

import flab.delideli.dao.OwnerDao;
import flab.delideli.dto.LoginDTO;
import flab.delideli.dto.OwnerDTO;
import flab.delideli.encrypt.Encryption;
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
			throw new IllegalArgumentException("이미 존재하는 회원입니다.");
		}

		String encryptPassword = encryption.encrypt(owner.getOwnerPassword());
		OwnerDTO encryptOwner = new OwnerDTO(owner.getOwnerId(), encryptPassword,
			owner.getOwnerName(), owner.getOwnerPhone(), owner.getOwnerAddress());

		ownerDao.insertOwner(encryptOwner);

	}

	public void loginOwner(LoginDTO loginDTO) {

		if(!isExistOwnerInfo(loginDTO)) {
			throw new IllegalArgumentException("아이디 혹은 비밀번호가 일치하지 않습니다.");
		}

	}

	public boolean isExistOwnerId(String ownerId) {
		return ownerDao.isExistOwnerId(ownerId);
	}

	public boolean isExistOwnerInfo(LoginDTO loginDTO) {

		String loginId = loginDTO.getLoginid();
		String loginPassword = encryption.encrypt(loginDTO.getLoginPassword());

		return ownerDao.isExistOwnerInfo(loginId, loginPassword);

	}

}
