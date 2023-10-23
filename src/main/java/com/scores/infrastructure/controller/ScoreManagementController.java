package com.scores.infrastructure.controller;

import com.scores.application.service.ScoreManagementService;
import com.scores.domain.controller.MessageRS;
import com.scores.domain.controller.score.rq.AddPointsUserRQ;
import com.scores.domain.controller.score.rq.ReportUserRQ;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/score")
@Tag(name = "API user score management ", description = "Service for managing user scores")
public class ScoreManagementController {

	private final ScoreManagementService scoreManagementService;

	public ScoreManagementController(ScoreManagementService scoreManagementService) {

		this.scoreManagementService = scoreManagementService;
	}


	@PostMapping(value = "/add",
		produces = {"application/json"})
	@Operation(summary = "Add points to User")
	public ResponseEntity<MessageRS> addPointsUser(@RequestBody AddPointsUserRQ assignScoreUserRQ) {

		var addRS = scoreManagementService.addPointsUser(assignScoreUserRQ);

		return ResponseEntity.ok(addRS);
	}

	@PostMapping(value = "/report",
		produces = {"application/json"})
	@Operation(summary = "Report to User")
	public ResponseEntity<MessageRS> reportUser(@RequestBody ReportUserRQ reportUser) {

		var reportRS = scoreManagementService.reportUser(reportUser);

		return ResponseEntity.ok(reportRS);
	}
}
