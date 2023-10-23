package com.scores.infrastructure.repository.adapter;

import com.scores.infrastructure.repository.UserDataRepository;
import com.scores.infrastructure.repository.entity.UserData;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserDataRepositoryAdapter {

	private final UserDataRepository userDataRepository;

	public UserDataRepositoryAdapter(UserDataRepository userDataRepository) {

		this.userDataRepository = userDataRepository;
	}

	/**
	 * Method for get User by userCode
	 *
	 * @return UserData
	 */
	public UserData findUserByUserCode(String userCode) throws Exception {

		return userDataRepository.findById(userCode)
			.orElseThrow(() -> new Exception(
				String.format("The user with that code %s does not exist.",
							  userCode)));
	}

	/**
	 * Method for get User List
	 *
	 * @return List UserData
	 */
	public List<UserData> findUsers() {

		return userDataRepository.findAll();
	}

	/**
	 * Method for get User by userCode
	 */
	@Transactional
	public void saveUser(UserData userData) {

		userDataRepository.save(userData);
	}
}
