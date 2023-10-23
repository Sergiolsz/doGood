package com.scores.application.service;

import com.scores.domain.controller.MessageRS;
import com.scores.domain.controller.data.rq.AddUserRQ;
import com.scores.domain.controller.data.rs.UsersRS;
import java.util.List;

public interface CrudUser {

	MessageRS addUser(AddUserRQ addUserRQ);

	MessageRS getUser(String userCode);

	List<UsersRS> getUsers();
}
