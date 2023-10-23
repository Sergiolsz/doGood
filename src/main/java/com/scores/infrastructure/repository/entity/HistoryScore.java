package com.scores.infrastructure.repository.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "HISTORY_SCORE")
public class HistoryScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "USER_CODE")
	private String userCode;

	private String action;

	private int score;

	private String achievement;

	private String message;

	@CreationTimestamp
	private LocalDateTime registered;

}
