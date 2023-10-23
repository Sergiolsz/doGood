package com.scores.infrastructure.repository.adapter;

import com.scores.infrastructure.repository.HistoryScoreRepository;
import com.scores.infrastructure.repository.entity.HistoryScore;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryScoreRepositoryAdapter {

	private final HistoryScoreRepository historyScoreRepository;

	public HistoryScoreRepositoryAdapter(HistoryScoreRepository historyScoreRepository) {

		this.historyScoreRepository = historyScoreRepository;
	}

	/**
	 * Method for add UserScore
	 */
	@Transactional
	public void saveHistory(HistoryScore historyScore) {

		historyScoreRepository.save(historyScore);
	}
}
