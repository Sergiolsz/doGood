package com.scores.domain.ports.data;

import com.scores.domain.dto.data.UserDataDTO;

public interface ConsultUserPointsPort {

	UserDataDTO processConsultUserPointsDB(String userCode) throws Exception;
}
