package com.scores.application.service;

import com.scores.domain.controller.MessageRS;
import com.scores.domain.controller.score.rq.AddPointsUserRQ;
import com.scores.domain.controller.score.rq.ReportUserRQ;
import com.scores.domain.ports.score.ScoreAddPointsPort;
import com.scores.domain.ports.score.ScoreReportPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScoreManagementService implements ScoreManagement {

	private final ScoreAddPointsPort scoreAddPointsPort;
	private final ScoreReportPort scoreReportPort;

	public ScoreManagementService(ScoreAddPointsPort scoreAddPointsPort, ScoreReportPort scoreReportPort) {

		this.scoreAddPointsPort = scoreAddPointsPort;
		this.scoreReportPort = scoreReportPort;
	}

	@Override
	public MessageRS addPointsUser(AddPointsUserRQ addPointsUserRQ) {

		try {
			log.info("Call to processMethodsScoreDB");
			var response = scoreAddPointsPort.postAddPoints(addPointsUserRQ);

			if (response) {
				return MessageRS.createMessage(null,
											   String.format("Assigned %s point/s to the user %s",
															 addPointsUserRQ.getScore(),
															 addPointsUserRQ.getUserCode()));
			} else {
				log.error("Call error to processMethodsScoreDB");

				return MessageRS.createMessage(null,
											   "Error during user points update process");
			}
		} catch (Exception e) {
			log.error(String.format("Call error to assignScoreUser. Exception: %s",
									e.getMessage()));

			return MessageRS.createMessage(null,
										   String.format("Throw Exception: %s",
														 e.getMessage()));
		}
	}

	@Override
	public MessageRS reportUser(ReportUserRQ reportUserRQ) {

		try {
			log.info("Call to postReportToUserDB");
			var response = scoreReportPort.postReportUser(reportUserRQ);

			if (response) {
				return MessageRS.createMessage(null,
											   String.format("User %s reported correctly",
															 reportUserRQ.getUserCode()));
			} else {
				log.error("Call error to postReportToUserDB");

				return MessageRS.createMessage(null,
											   "Error during user report process");
			}
		} catch (Exception e) {
			log.error(String.format("Call error to postReportToUserDB. Exception: %s",
									e.getMessage()));

			return MessageRS.createMessage(null,
										   String.format("Throw Exception: %s",
														 e.getMessage()));
		}
	}
}
