package com.scores.domain.controller.data.rs;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UsersRS {

	String userCode;
	String name;
	int score;
	String achievement;
}
