package com.scores.application.service;

import com.scores.domain.controller.MessageRS;
import com.scores.domain.controller.data.rs.RankingRS;
import com.scores.domain.controller.data.rs.UserRankingRS;
import com.scores.domain.dto.data.UserDataDTO;
import com.scores.domain.ports.data.ConsultRankingPort;
import com.scores.domain.ports.data.ConsultUserAchivementPort;
import com.scores.domain.ports.data.ConsultUserPointsPort;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsultDataService implements ConsultData {


	private final ConsultUserPointsPort consultUserPointsPort;
	private final ConsultUserAchivementPort consultUserAchivementPort;
	private final ConsultRankingPort consultRankingPort;

	public ConsultDataService(
		ConsultUserPointsPort consultUserPointsPort,
		ConsultUserAchivementPort consultUserAchivementPort,
		ConsultRankingPort consultRankingPort) {

		this.consultUserPointsPort = consultUserPointsPort;
		this.consultUserAchivementPort = consultUserAchivementPort;
		this.consultRankingPort = consultRankingPort;
	}


	@Override
	public MessageRS consultPointsDTO(String userCode) {

		try {
			log.info("Call to consultUserPointsDTO");
			var userPoints = consultUserPointsPort.processConsultUserPointsDB(userCode);

			log.info("Process Call to consultUserPointsDTO correct");

			return MessageRS.createMessage(null,
										   String.format("User %s points are: %s",
														 userPoints.getUserCode(),
														 userPoints.getScore()));
		} catch (Exception e) {
			log.error(String.format("Call error to consultUserPointsDTO. Exception: %s",
									e.getMessage()));

			return MessageRS.createMessage(null,
										   String.format("Throw Exception: %s",
														 e.getMessage()));
		}
	}

	@Override
	public MessageRS consultAchievementsDTO(String userCode) {

		try {
			log.info("Call to consultUserAchievementsDTO");
			var userAchievements = consultUserAchivementPort.processConsultUserAchivementDB(userCode);

			log.info("Process Call to consultUserAchievementsDTO correct");

			return MessageRS.createMessage(null,
										   String.format("User %s achievement are: %s",
														 userAchievements.getUserCode(),
														 userAchievements.getAchievement()));
		} catch (Exception e) {
			log.error(String.format("Call error to consultUserAchievementsDTO. Exception: %s",
									e.getMessage()));

			return MessageRS.createMessage(null,
										   String.format("Throw Exception: %s",
														 e.getMessage()));
		}
	}

	@Override
	public RankingRS consultRankingDTO() {

		log.info("Call to consultRankingDTO");

		var rankingList = new ArrayList<UserRankingRS>();

		consultRankingPort.processConsultRankingDB()
			.stream()
			.sorted(Comparator
						.comparing(UserDataDTO::getScore).reversed()
						.thenComparing(UserDataDTO::getName))
			.toList()
			.stream()
			.collect(indexed())
			.forEach((number, user) -> {
				rankingList.add(UserRankingRS.builder()
									.position(number)
									.name(user.getName())
									.score(user.getScore())
									.build());
			});

		log.info("Process Call to consultRankingDTO correct");
		return RankingRS.builder().ranking(rankingList).build();
	}

	/**
	 * Method to obtain the index of a list
	 *
	 * @param <T> Index
	 * @param <R> Value
	 *
	 * @return LinkedHashMap
	 */
	private static <T, R> Collector<T, ?, Map<Integer, T>> indexed() {

		return Collector.of(
			LinkedHashMap::new,
			(map, element) -> map.put(map.size() + 1, element),
			(left, right) -> {
				left.putAll(right);
				return left;
			});
	}

}
