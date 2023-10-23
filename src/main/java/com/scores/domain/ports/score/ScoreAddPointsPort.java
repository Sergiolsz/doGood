package com.scores.domain.ports.score;


import com.scores.domain.controller.score.rq.AddPointsUserRQ;

public interface ScoreAddPointsPort {

	boolean postAddPoints(AddPointsUserRQ assignScoreUserRQ) throws Exception;
}
