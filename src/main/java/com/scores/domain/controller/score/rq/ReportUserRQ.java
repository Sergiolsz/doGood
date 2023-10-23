package com.scores.domain.controller.score.rq;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ReportUserRQ {

	String userCode;
	String message;

}
