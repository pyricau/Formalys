package com.excilys.formation.formalys.shared;

import java.io.Serializable;
import java.util.Date;

public class TrainingDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String authorNickname;
	private Date createdAt;
	private QuestionState questionState;
	private int questionCount;

	@SuppressWarnings("unused")
	private TrainingDTO() {
	}

	public TrainingDTO(Long id, String name, String authorNickname, Date createdAt, QuestionState questionState, int questionCount) {
		this.id = id;
		this.name = name;
		this.authorNickname = authorNickname;
		this.createdAt = createdAt;
		this.questionState = questionState;
		this.questionCount = questionCount;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAuthorNickname() {
		return authorNickname;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public QuestionState getQuestionState() {
		return questionState;
	}
	
	public int getQuestionCount() {
		return questionCount;
	}

}
