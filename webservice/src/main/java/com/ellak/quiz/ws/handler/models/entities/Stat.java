package com.ellak.quiz.ws.handler.models.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "stats")
public class Stat implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long user_id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category")
	private String category;
	
	@Column(name = "score")
	private Double score;

	@Column(name = "tries")
	private Integer tries;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getTries() {
		return tries;
	}

	public void setTries(Integer tries) {
		this.tries = tries;
	}
}
