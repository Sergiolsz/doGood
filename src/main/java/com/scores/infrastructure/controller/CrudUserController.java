package com.scores.infrastructure.controller;

import com.scores.application.service.CrudUserService;
import com.scores.domain.controller.MessageRS;
import com.scores.domain.controller.data.rq.AddUserRQ;
import com.scores.domain.controller.data.rs.UsersRS;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "CRUD user management", description = "CRUD service for user management")
public class CrudUserController {

	private final CrudUserService crudUserService;

	public CrudUserController(CrudUserService crudUserService) {

		this.crudUserService = crudUserService;
	}

	@PostMapping(produces = {"application/json"})
	@Operation(summary = "Add User")
	public ResponseEntity<MessageRS> addUser(@RequestBody AddUserRQ addUserRQ) {

		var messageRS = crudUserService.addUser(addUserRQ);

		return ResponseEntity.ok(messageRS);
	}

	@GetMapping(produces = {"application/json"})
	@Operation(summary = "Get User")
	public ResponseEntity<MessageRS> getUser(@RequestParam String userCode) {

		var messageRS = crudUserService.getUser(userCode);

		return ResponseEntity.ok(messageRS);
	}

	@GetMapping(value = "/list",
		produces = {"application/json"})
	@Operation(summary = "Get Users")
	public ResponseEntity<List<UsersRS>> getUsers() {

		var users = crudUserService.getUsers();

		return ResponseEntity.ok(users);
	}
}
