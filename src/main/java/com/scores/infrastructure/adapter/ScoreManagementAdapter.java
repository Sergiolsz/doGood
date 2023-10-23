package com.scores.infrastructure.adapter;

import com.scores.domain.controller.score.rq.AddPointsUserRQ;
import com.scores.domain.controller.score.rq.ReportUserRQ;
import com.scores.domain.dto.history.HistoryScoreDTO;
import com.scores.domain.mapper.MapperUser;
import com.scores.infrastructure.repository.adapter.HistoryScoreRepositoryAdapter;
import com.scores.infrastructure.repository.adapter.UserDataRepositoryAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScoreManagementAdapter {

	private final UserDataRepositoryAdapter userRepository;
	private final HistoryScoreRepositoryAdapter historyRepository;

	public ScoreManagementAdapter(UserDataRepositoryAdapter userRepository, HistoryScoreRepositoryAdapter historyRepository) {

		this.userRepository = userRepository;
		this.historyRepository = historyRepository;
	}

	/**
	 * Process for assigning points for a user
	 *
	 * @return True if ok | False if ko
	 */
	public boolean processAddingPointsToUser(AddPointsUserRQ addPointsUserRQ) throws Exception {

		var userData = userRepository.findUserByUserCode(addPointsUserRQ.getUserCode());
		log.info(String.format("User found. Code: %s", userData.getUserCode()));

		userData.setScore(userData.getScore() + addPointsUserRQ.getScore());
		userData.setAchievement(checkUserPoints(userData.getScore()));

		try {
			userRepository.saveUser(userData);
			log.info("User updated correctly with points added.");

			var history = HistoryScoreDTO.builder()
				.userCode(addPointsUserRQ.getUserCode())
				.action("Added")
				.score(addPointsUserRQ.getScore())
				.achievement(userData.getAchievement())
				.message(addPointsUserRQ.getMessage())
				.build();

			historyRepository.saveHistory(MapperUser.MAPPER_USER.dtoHistoryEntity(history));
			log.info("Added new history successfully");
			return true;
		} catch (Exception e) {
			log.error(String.format("Error during adding process. Exception: %s",
									e.getMessage()));
			return false;
		}

	}

	/**
	 * Process to report a user. A little is subtracted for each report. You can add a message for the reason for the report
	 *
	 * @return True if ok | False if ko
	 */
	public boolean processReportingToUser(ReportUserRQ reportUserRQ) throws Exception {

		var userData = userRepository.findUserByUserCode(reportUserRQ.getUserCode());
		log.info(String.format("User found. Code: %s", userData.getUserCode()));

		userData.setScore(userData.getScore() - 1);
		userData.setAchievement(checkUserPoints(userData.getScore()));

		try {
			userRepository.saveUser(userData);
			log.info("User updated correctly with report added.");

			var history = HistoryScoreDTO.builder()
				.userCode(reportUserRQ.getUserCode())
				.action("Reported")
				.score(userData.getScore())
				.achievement(userData.getAchievement())
				.message(reportUserRQ.getMessage())
				.build();

			historyRepository.saveHistory(MapperUser.MAPPER_USER.dtoHistoryEntity(history));
			log.info("Added new history successfully");
			return true;
		} catch (Exception e) {
			log.error(String.format("Error during reporting process. Exception: %s",
									e.getMessage()));
			return false;
		}
	}

	/**
	 * Method to assign an achievement to the user based on their score
	 *
	 * @return achievement
	 */
	private String checkUserPoints(int score) {

		return switch (score / 10) {
			case 1 -> "Bienvenida";
			case 2 -> "Capitan Planeta";
			case 3 -> "Greenpeace";
			default -> "sin logro";
		};

	}
}
