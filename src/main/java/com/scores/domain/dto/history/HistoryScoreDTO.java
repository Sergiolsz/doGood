package com.scores.domain.dto.history;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Value
public class HistoryScoreDTO {

	String userCode;
	String action;
	int score;
	String achievement;
	String message;
}
