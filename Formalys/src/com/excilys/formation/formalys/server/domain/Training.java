package com.excilys.formation.formalys.server.domain;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.users.User;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@SuppressWarnings("unused")
public class Training {

	public enum QuestionState {
		CREATE_QUESTIONS, ANSWER_QUESTIONS, REVIEW_ANSWERS
	}

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private User author;

	@Persistent
	private Date createdAt;

	@Persistent
	private QuestionState state;

	@Persistent
	private String name;

	public Training(User author, String name) {
		this.author = author;
		this.name = name;
		createdAt = new Date();
		state = QuestionState.CREATE_QUESTIONS;
	}
	
	public Question newQuestion(User questionAuthor, String content) {
		return new Question(id, questionAuthor, content);
	}

}
