package com.scores.domain.controller.score.rq;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AddPointsUserRQ {

	String userCode;
	int score;
	String message;
}
