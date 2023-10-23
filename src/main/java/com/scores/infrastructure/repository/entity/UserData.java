package com.scores.infrastructure.repository.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "USER")
public class UserData {

	@Id
	@Column(name = "USER_CODE")
	private String userCode;

	private String name;

	private String company;

	private String job;

	private int score;

	private String achievement;

	@UpdateTimestamp
	private LocalDateTime registered;
}
