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
public class Answer {

	public enum State {
		NEW, DELETED
	}

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private User author;

	@Persistent
	private Date createdAt;

	@Persistent
	private State state;

	@Persistent
	private String content;

	@Persistent
	private Long questionId;

	Answer(Long questionId, User author, String content) {
		this.questionId = questionId;
		this.author = author;
		this.content = content;
		createdAt = new Date();
		state = State.NEW;
	}

}
