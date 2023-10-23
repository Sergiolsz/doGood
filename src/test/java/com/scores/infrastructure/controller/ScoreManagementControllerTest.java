package com.scores.infrastructure.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

import com.scores.application.service.ScoreManagementService;
import com.scores.domain.controller.MessageRS;
import com.scores.domain.controller.score.rq.AddPointsUserRQ;
import com.scores.domain.controller.score.rq.ReportUserRQ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class ScoreManagementControllerTest {

	@InjectMocks
	ScoreManagementController scoreManagementController;

	@Mock
	ScoreManagementService scoreManagementService;

	private AddPointsUserRQ addPointsUserRQ;
	private ReportUserRQ reportUserRQ;
	private MessageRS messageRS;

	@BeforeEach
	void setUp() {

		addPointsUserRQ = AddPointsUserRQ.builder()
			.userCode("1111")
			.score(10)
			.message("Points for completing a survey.")
			.build();

		reportUserRQ = ReportUserRQ.builder()
			.userCode("1111")
			.message("Do not take any surveys during the week.")
			.build();
	}

	@Test
	void addPointsUser() {

		messageRS = MessageRS.builder().message("Add points correctly.").build();

		given(scoreManagementService.addPointsUser(addPointsUserRQ)).willReturn(messageRS);

		final var response = scoreManagementController.addPointsUser(addPointsUserRQ);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getUser()).isNull();
		assertThat(response.getBody().getMessage()).isNotNull();
		assertThat(response.getBody().getMessage()).isEqualTo("Add points correctly.");

	}

	@Test
	void reportUser() {

		messageRS = MessageRS.builder().message(String.format("User %s reported correctly",
															  reportUserRQ.getUserCode())).build();

		given(scoreManagementService.reportUser(reportUserRQ)).willReturn(messageRS);

		final var response = scoreManagementController.reportUser(reportUserRQ);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getUser()).isNull();
		assertThat(response.getBody().getMessage()).isNotNull();
		assertThat(response.getBody().getMessage()).isEqualTo(String.format("User %s reported correctly",
																			reportUserRQ.getUserCode()));
	}

}