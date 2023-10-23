package com.scores.domain.controller.data.rq;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AddUserRQ {

	private String userCode;
	private String name;
	private String company;
	private String job;
}
