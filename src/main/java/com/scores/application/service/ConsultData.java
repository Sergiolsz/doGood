package com.scores.application.service;

import com.scores.domain.controller.MessageRS;
import com.scores.domain.controller.data.rq.AddUserRQ;
import com.scores.domain.controller.data.rs.RankingRS;
import com.scores.domain.controller.data.rs.UsersRS;
import java.util.List;

public interface ConsultData {

	MessageRS consultPointsDTO(String userCode);

	MessageRS consultAchievementsDTO(String userCode);

	RankingRS consultRankingDTO();

}
