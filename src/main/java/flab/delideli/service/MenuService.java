package flab.delideli.service;

import flab.delideli.dao.MenuDao;
import flab.delideli.dto.MenuDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

	private final MenuDao menuDao;

	public void addMenu(MenuDTO menuDTO) {
		menuDao.insertMenu(menuDTO);
	}

}
