package com.scores.application.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.scores.domain.controller.MessageRS;
import com.scores.domain.controller.score.rq.AddPointsUserRQ;
import com.scores.domain.controller.score.rq.ReportUserRQ;
import com.scores.domain.ports.score.ScoreAddPointsPort;
import com.scores.domain.ports.score.ScoreReportPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ScoreManagementServiceTest {

	@InjectMocks
	private ScoreManagementService scoreManagementService;

	@Mock
	private ScoreAddPointsPort scoreAddPointsPort;

	@Mock
	private ScoreReportPort scoreReportPort;

	private AddPointsUserRQ addPointsUserRQ;
	private ReportUserRQ reportUserRQ;

	@BeforeEach
	void setUp() {

		addPointsUserRQ = AddPointsUserRQ.builder()
			.userCode("12345AB")
			.score(10)
			.message("Points for completing a survey")
			.build();

		reportUserRQ = ReportUserRQ.builder()
			.userCode("12345AB")
			.message("Report for not logging in within 24 hours")
			.build();
	}

	@Test
	void addPointsUser_given_True() throws Exception {

		given(scoreAddPointsPort.postAddPoints(addPointsUserRQ)).willReturn(true);

		var response = scoreManagementService.addPointsUser(addPointsUserRQ);

		assertThat(response.getUser()).isNull();
		assertThat(response.getMessage())
			.isEqualTo(String.format("Assigned %s point/s to the user %s",
									 addPointsUserRQ.getScore(),
									 addPointsUserRQ.getUserCode()));
	}

	@Test
	void addPointsUser_given_False() throws Exception {

		given(scoreAddPointsPort.postAddPoints(addPointsUserRQ)).willReturn(false);

		var response = scoreManagementService.addPointsUser(addPointsUserRQ);

		assertThat(response.getUser()).isNull();
		assertThat(response.getMessage()).isEqualTo("Error during user points update process");
	}

	@Test
	void addPointsUser_given_Exception() throws Exception {

		when(scoreAddPointsPort.postAddPoints(addPointsUserRQ)).thenThrow(Exception.class);

		final MessageRS messageRS = scoreManagementService.addPointsUser(addPointsUserRQ);

		assertThat(messageRS.getUser()).isNull();
		assertThat(messageRS.getMessage()).contains("Throw Exception: ");
	}

	@Test
	void reportUser_given_True() throws Exception {

		given(scoreReportPort.postReportUser(reportUserRQ)).willReturn(true);

		var response = scoreManagementService.reportUser(reportUserRQ);

		assertThat(response.getUser()).isNull();
		assertThat(response.getMessage())
			.isEqualTo(String.format("User %s reported correctly",
									 reportUserRQ.getUserCode()));
	}

	@Test
	void reportUser_given_False() throws Exception {

		given(scoreReportPort.postReportUser(reportUserRQ)).willReturn(false);

		var response = scoreManagementService.reportUser(reportUserRQ);

		assertThat(response.getUser()).isNull();
		assertThat(response.getMessage()).isEqualTo("Error during user report process");
	}

	@Test
	void reportUser_given_Exception() throws Exception {

		given(scoreReportPort.postReportUser(reportUserRQ)).willThrow(Exception.class);

		final MessageRS messageRS = scoreManagementService.reportUser(reportUserRQ);

		assertThat(messageRS.getUser()).isNull();
		assertThat(messageRS.getMessage()).contains("Throw Exception: ");
	}
}