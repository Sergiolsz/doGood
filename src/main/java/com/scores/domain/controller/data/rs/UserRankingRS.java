package com.scores.domain.controller.data.rs;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UserRankingRS {

	private int position;
	private String name;
	private int score;
}
