package com.scores.infrastructure.controller;

import com.scores.application.service.ConsultDataService;
import com.scores.domain.controller.MessageRS;
import com.scores.domain.controller.data.rq.AddUserRQ;
import com.scores.domain.controller.data.rs.RankingRS;
import com.scores.domain.controller.data.rs.UsersRS;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collections;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consult")
@Tag(name = "API consult user data", description = "Service for consulting user data")
public class ConsultDataController {

	private final ConsultDataService consultDataService;

	public ConsultDataController(ConsultDataService consultDataService) {

		this.consultDataService = consultDataService;
	}


	@GetMapping(value = "/points",
		produces = {"application/json"})
	@Operation(summary = "Consult User points")
	public ResponseEntity<MessageRS> consultPoints(@RequestParam String userCode) {

		var messageRS = consultDataService.consultPointsDTO(userCode);

		return ResponseEntity.ok(messageRS);
	}

	@GetMapping(value = "/achievement",
		produces = {"application/json"})
	@Operation(summary = "Consult User achievement")
	public ResponseEntity<MessageRS> consultAchievement(@RequestParam String userCode) {

		var messageRS = consultDataService.consultAchievementsDTO(userCode);

		return ResponseEntity.ok(messageRS);
	}

	@GetMapping(value = "/ranking",
		produces = {"application/json"})
	@Operation(summary = "Consult Ranking Users")
	public ResponseEntity<RankingRS> consultRanking() {

		var rankingRS = consultDataService.consultRankingDTO();

		return ResponseEntity.ok(rankingRS);
	}
}
