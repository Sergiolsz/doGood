package com.scores.domain.controller.data.rs;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RankingRS {

	List<UserRankingRS> ranking;
}
