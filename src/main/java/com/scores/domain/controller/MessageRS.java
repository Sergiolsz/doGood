package com.scores.domain.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.scores.domain.dto.data.UserDataDTO;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@JsonInclude(Include.NON_NULL)
public class MessageRS {

	UserDataDTO user;
	String message;

	public static MessageRS createMessage(UserDataDTO userDataDTO, String message) {

		return MessageRS
			.builder()
			.user(userDataDTO)
			.message(message)
			.build();
	}
}
