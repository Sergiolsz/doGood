package com.scores.domain.ports.data;

import com.scores.domain.dto.data.UserDataDTO;
import java.util.List;

public interface ConsultRankingPort {

	List<UserDataDTO> processConsultRankingDB();
}
