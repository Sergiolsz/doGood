package com.scores.domain.ports.data;

import com.scores.domain.dto.data.UserDataDTO;

public interface ConsultUserAchivementPort {

	UserDataDTO processConsultUserAchivementDB(String userCode) throws Exception;
}
