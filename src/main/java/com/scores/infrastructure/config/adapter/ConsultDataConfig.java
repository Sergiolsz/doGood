package com.scores.infrastructure.config.adapter;

import com.scores.domain.ports.data.ConsultRankingPort;
import com.scores.domain.ports.data.ConsultUserAchivementPort;
import com.scores.domain.ports.data.ConsultUserPointsPort;
import com.scores.infrastructure.adapter.CrudUserAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultDataConfig {

	@Bean
	public ConsultUserPointsPort consultUserPointsImpl(CrudUserAdapter crudUserAdapterAdapter) {

		return crudUserAdapterAdapter::processGetUser;
	}

	@Bean
	public ConsultUserAchivementPort consultAchivementUserImpl(CrudUserAdapter crudUserAdapterAdapter) {

		return crudUserAdapterAdapter::processGetUser;
	}

	@Bean
	public ConsultRankingPort consultRankingImpl(CrudUserAdapter crudUserAdapterAdapter) {

		return crudUserAdapterAdapter::processGetUsers;
	}
}
