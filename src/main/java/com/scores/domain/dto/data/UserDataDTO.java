package com.scores.domain.dto.data;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDataDTO {

	private String userCode;
	private String name;
	private String company;
	private String job;
	private int score;
	private String achievement;
}
