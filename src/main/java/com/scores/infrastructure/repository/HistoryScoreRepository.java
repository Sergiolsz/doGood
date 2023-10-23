package com.scores.infrastructure.repository;

import com.scores.infrastructure.repository.entity.HistoryScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HistoryScoreRepository extends JpaRepository<HistoryScore, String> {
}
