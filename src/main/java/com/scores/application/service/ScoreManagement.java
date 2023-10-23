package com.scores.application.service;

import com.scores.domain.controller.score.rq.AddPointsUserRQ;
import com.scores.domain.controller.score.rq.ReportUserRQ;
import com.scores.domain.controller.MessageRS;

public interface ScoreManagement {

	MessageRS addPointsUser(AddPointsUserRQ assignScoreUserRQ);

	MessageRS reportUser(ReportUserRQ managementUserRQ);

}
