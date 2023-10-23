package com.scores.domain.ports.data;

import com.scores.domain.dto.data.UserDataDTO;
import java.util.List;

public interface CrudUserPort {

	void processAddUser(UserDataDTO userDataDTO);

	UserDataDTO processGetUser(String userCode) throws Exception;

	List<UserDataDTO> processGetUsers();
}
