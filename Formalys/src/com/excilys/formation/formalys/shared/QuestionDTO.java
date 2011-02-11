package com.excilys.formation.formalys.shared;

import java.io.Serializable;
import java.util.Date;

public class QuestionDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String content;
	private String nickname;
	private Date createdAt;
	private boolean answered;

	@SuppressWarnings("unused")
	private QuestionDTO() {
	}

	public QuestionDTO(Long id, String content, String nickname, Date createdAt, boolean answered) {
		this.id = id;
		this.content = content;
		this.nickname = nickname;
		this.createdAt = createdAt;
		this.answered = answered;
	}

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public String getNickname() {
		return nickname;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	
	public boolean hasAnswered() {
		return answered;
	}
	
	

}
