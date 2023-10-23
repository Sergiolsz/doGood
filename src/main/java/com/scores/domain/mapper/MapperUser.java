package com.scores.domain.mapper;

import com.scores.domain.controller.data.rq.AddUserRQ;
import com.scores.domain.dto.data.UserDataDTO;
import com.scores.domain.dto.history.HistoryScoreDTO;
import com.scores.infrastructure.repository.entity.HistoryScore;
import com.scores.infrastructure.repository.entity.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapperUser {

	MapperUser MAPPER_USER = Mappers.getMapper(MapperUser.class);

	/**
	 * DTO to Entity
	 */
	UserData dtoUserDataEntity(UserDataDTO userDataDTO);

	HistoryScore dtoHistoryEntity(HistoryScoreDTO historyScoreDTO);


	/**
	 * Entity to DTO
	 */

	UserDataDTO entityUserDataDTO(UserData userData);

	HistoryScoreDTO entityHistoryDTO(HistoryScore historyScore);


	/**
	 * RQ to DTO
	 */
	UserDataDTO addUserRQUserDataDTO(AddUserRQ addUserRQ);

}
