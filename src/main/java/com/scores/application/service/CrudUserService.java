package com.scores.application.service;

import com.scores.domain.controller.MessageRS;
import com.scores.domain.controller.data.rq.AddUserRQ;
import com.scores.domain.controller.data.rs.UsersRS;
import com.scores.domain.mapper.MapperUser;
import com.scores.domain.ports.data.CrudUserPort;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CrudUserService implements CrudUser {

	private final CrudUserPort crudUserPort;

	public CrudUserService(CrudUserPort crudUserPort) {

		this.crudUserPort = crudUserPort;
	}

	@Override
	public MessageRS addUser(AddUserRQ addUserRQ) {

		log.info("Call to processAddUserDataDB");
		crudUserPort.processAddUser(MapperUser.MAPPER_USER.addUserRQUserDataDTO(addUserRQ));

		log.info("Process Call to processAddUserDataDB correct");

		return MessageRS.createMessage(null,
									   "Successfully created user");
	}

	@Override
	public MessageRS getUser(String userCode) {

		try {
			log.info("Call to getUserDataDB");
			var user = crudUserPort.processGetUser(userCode);

			log.info("Process Call to getUserDataDB correct");

			return MessageRS.createMessage(user, "Successfully get user");
		} catch (Exception e) {
			log.error(String.format("Call error to getUserDataDB. Exception: %s",
									e.getMessage()));

			return MessageRS.createMessage(null,
										   String.format("Throw Exception: %s",
														 e.getMessage()));
		}
	}

	@Override
	public List<UsersRS> getUsers() {

		log.info("Call to getUserDataDB");
		var users = crudUserPort.processGetUsers()
			.stream()
			.map(dto -> UsersRS.builder()
				.userCode(dto.getUserCode())
				.name(dto.getName())
				.score(dto.getScore())
				.achievement(dto.getAchievement())
				.build())
			.toList();

		if (users.isEmpty()) {
			log.error("Call to getUserDataDB response a empty list");

			return Collections.emptyList();
		}

		log.info("Process Call to getUserDataDB correct");

		return users;
	}
}
