package flab.delideli.service;

import flab.delideli.dto.PushDTO;
import org.springframework.stereotype.Service;

@Service
public class PushAppAlarmService implements PushService {

	@Override
	public void sendToOwner(PushDTO pushDTO, String ownerId) {

	}

	@Override
	public void sendToUser(PushDTO pushDTO, String userId) {

	}

}