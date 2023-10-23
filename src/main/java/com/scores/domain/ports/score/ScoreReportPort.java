package com.scores.domain.ports.score;

import com.scores.domain.controller.score.rq.ReportUserRQ;

public interface ScoreReportPort {

	boolean postReportUser(ReportUserRQ reportUserRQ) throws Exception;
}
