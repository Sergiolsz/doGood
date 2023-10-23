package com.scores.infrastructure.adapter;

import com.scores.domain.dto.data.UserDataDTO;
import com.scores.domain.mapper.MapperUser;
import com.scores.domain.ports.data.CrudUserPort;
import com.scores.infrastructure.repository.adapter.UserDataRepositoryAdapter;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CrudUserAdapter implements CrudUserPort {

	private final UserDataRepositoryAdapter userRepository;

	public CrudUserAdapter(UserDataRepositoryAdapter userRepository) {

		this.userRepository = userRepository;
	}

	@Override
	public void processAddUser(UserDataDTO userDataDTO) {
		userRepository.saveUser(MapperUser.MAPPER_USER.dtoUserDataEntity(userDataDTO));
	}

	@Override
	public UserDataDTO processGetUser(String userCode) throws Exception {

		return MapperUser.MAPPER_USER.entityUserDataDTO(userRepository.findUserByUserCode(userCode));
	}

	@Override
	public List<UserDataDTO> processGetUsers() {

		return userRepository.findUsers()
			.stream()
			.map(MapperUser.MAPPER_USER::entityUserDataDTO)
			.toList();
	}
}
