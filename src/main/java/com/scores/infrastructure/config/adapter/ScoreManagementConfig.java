package com.scores.infrastructure.config.adapter;

import com.scores.domain.ports.score.ScoreAddPointsPort;
import com.scores.domain.ports.score.ScoreReportPort;
import com.scores.infrastructure.adapter.ScoreManagementAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScoreManagementConfig {

	@Bean
	public ScoreAddPointsPort addPointsUserImpl(ScoreManagementAdapter scoreManagementAdapter) {
		return scoreManagementAdapter::processAddingPointsToUser;
	}

	@Bean
	public ScoreReportPort reportUserImpl(ScoreManagementAdapter scoreManagementAdapter) {
		return scoreManagementAdapter::processReportingToUser;
	}
}
