package flab.delideli.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import flab.delideli.dto.OwnerDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OwnerServiceTest {

	@Autowired
	OwnerService ownerService;

	OwnerDTO owner1 = new OwnerDTO("sunday", "abcd123", "Sun",
		"010-1111-1234", "Seoul", "111-22-33334");
	OwnerDTO owner2 = new OwnerDTO("sunday", "abcd123", "Sun",
		"010-2222-1234", "Seoul", "111-33-33334");
	OwnerDTO owner3 = new OwnerDTO("monday", "abcd123", "Mon",
		"010-3333-1234", "Seoul", "111-44-33334");

	@BeforeEach
	public void beforeEach() {

		ownerService.testDeleteOwner(owner1.getOwnerId());
		ownerService.testDeleteOwner(owner2.getOwnerId());
		ownerService.testDeleteOwner(owner3.getOwnerId());

	}

	@Test
	public void 사장님_회원가입_테스트() {

		ownerService.joinOwner(owner1);
		OwnerDTO selectOwner = ownerService.testSelectOwner(owner1.getOwnerId());

		assertThat(selectOwner).isEqualTo(owner1);

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
	public void 사장님_중복_가입_테스트() {

		ownerService.joinOwner(owner1);

		IllegalStateException e = assertThrows(IllegalStateException.class,
			()->ownerService.joinOwner(owner2));

		assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");

	}

}
