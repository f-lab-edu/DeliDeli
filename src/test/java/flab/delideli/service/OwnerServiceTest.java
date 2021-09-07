package flab.delideli.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import flab.delideli.dao.LoginDao;
import flab.delideli.dto.LoginDTO;
import flab.delideli.dto.OwnerDTO;

import flab.delideli.exception.WrongLoginInfoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OwnerServiceTest {

	@Autowired
	OwnerService ownerService;

	@Autowired
	LoginService loginService;

	@Autowired
	LoginDao loginDao;

	OwnerDTO owner1 = new OwnerDTO("sunday", "abcd123", "Sun",
		"010-1111-1234", "Seoul");
	OwnerDTO owner2 = new OwnerDTO("sunday", "abcd123", "Sun",
		"010-2222-1234", "Seoul");
	OwnerDTO owner3 = new OwnerDTO("monday", "abcd123", "Mon",
		"010-3333-1234", "Seoul");

	LoginDTO loginDTO1 = new LoginDTO("sunday", "abcd123");
	LoginDTO loginDTO2 = new LoginDTO("monday", "abcd123");

	@BeforeEach
	public void beforeEach() {

		ownerService.deleteOwner(owner1.getOwnerId());
		ownerService.deleteOwner(owner2.getOwnerId());
		ownerService.deleteOwner(owner3.getOwnerId());

	}

	@Test
	public void 사장님_회원가입_테스트() {

		ownerService.joinOwner(owner1);
		String selectOwner = ownerService.selectOwnerId(owner1.getOwnerId());

		assertThat(selectOwner).isEqualTo(owner1.getOwnerId());

	}

	@Test
	public void 사장님_아이디_중복_체크_테스트() {

		ownerService.joinOwner(owner1);

		String ownerId2 = owner2.getOwnerId();
		String ownerId3 = owner3.getOwnerId();

		assertThat(ownerService.isExistOwnerId(ownerId2)).isTrue();
		assertThat(ownerService.isExistOwnerId(ownerId3)).isFalse();

	}

	@Test
	public void 사장님_중복_가입_예외_발생_테스트() {

		ownerService.joinOwner(owner1);

		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
			()->ownerService.joinOwner(owner2));

		assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");

	}

	@Test
	public void 사장님_로그인정보_존재여부_테스트() {

		ownerService.joinOwner(owner1);

		boolean isLoginOk1 = loginDao.isExistUserInfo(
			loginDTO1.getLoginid(), loginDTO1.getLoginPassword());
		boolean isLoginOk2 = loginDao.isExistUserInfo(
			loginDTO2.getLoginid(), loginDTO2.getLoginPassword());

		assertThat(isLoginOk1).isTrue();
		assertThat(isLoginOk2).isFalse();

	}

	@Test
	public void 사장님_로그인실패_예외_발생_테스트() {

		ownerService.joinOwner(owner1);

		WrongLoginInfoException e = assertThrows(WrongLoginInfoException.class,
			()->loginService.login(loginDTO2));

		assertEquals(e.getMessage(), "아이디 혹은 비밀번호가 일치하지 않습니다.");

	}

}
