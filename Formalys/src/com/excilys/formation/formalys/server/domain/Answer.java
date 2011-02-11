package com.excilys.formation.formalys.server.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.excilys.formation.formalys.shared.AnswerDTO;
import com.excilys.formation.formalys.shared.QuestionDTO;
import com.google.appengine.api.users.User;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@SuppressWarnings("unused")
public class Answer {

	public enum State {
		NEW, DELETED
	}
	

	public static List<AnswerDTO> getAsDtoByQuestionIdOrderedByCreatedAt(PersistenceManager pm, Long questionId) {
		List<Answer> answers = getByQuestionIdOrderedByCreatedAt(pm, questionId);
		
		return toDto(answers);
	}
	

	private static List<Answer> getByQuestionIdOrderedByCreatedAt(PersistenceManager pm, Long questionId) {
		Query query = pm.newQuery(Answer.class, "state=='NEW' && questionId == questionIdParam");
		query.declareParameters("Long questionIdParam");
		query.setOrdering("createdAt desc");
		@SuppressWarnings("unchecked")
		List<Answer> answers = (List<Answer>) query.execute(questionId);
		return answers;
	}


	private static List<AnswerDTO> toDto(List<Answer> answers) {
		List<AnswerDTO> answerDTOs = new ArrayList<AnswerDTO>();
		for (Answer answer : answers) {
			answerDTOs.add(answer.toDto());
		}
		return answerDTOs;
	}

	public static List<Long> getAnsweredQuestionIdsByTrainingIdAndUser(PersistenceManager pm, Long trainingId, User user) {
		Query query = pm.newQuery(Answer.class, "state=='NEW' && trainingId == trainingIdParam && author == authorParam");
		query.declareParameters("Long trainingId, com.google.appengine.api.users.User authorParam");
		
		@SuppressWarnings("unchecked")
		List<Answer> answers = (List<Answer>) query.execute(trainingId, user);
		
		List<Long> answeredQuestionIds = new ArrayList<Long>();
		for(Answer answer : answers) {
			answeredQuestionIds.add(answer.questionId);
		}
		
		return answeredQuestionIds;
	}

	public static boolean notAnsweredYet(PersistenceManager pm, Long questionId, User currentUser) {

		Query query = pm.newQuery(Answer.class, "state=='NEW' && questionId == questionIdParam && author == authorParam");
		query.declareParameters("Long questionIdParam, com.google.appengine.api.users.User authorParam");
		query.setRange(0, 1);

		@SuppressWarnings("unchecked")
		List<Answer> answers = (List<Answer>) query.execute(questionId, currentUser);

		return answers.size() == 0;
	}

	public static void create(PersistenceManager pm, Question question, User answerAuthor, String answerContent) {
		Answer answer = question.newAnswer(answerAuthor, answerContent);
		pm.makePersistent(answer);
	}

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String content;

	@Persistent
	private User author;

	@Persistent
	private Date createdAt;

	@Persistent
	private State state;

	@Persistent
	private Long questionId;
	
	@Persistent
	private Long trainingId;

	Answer(Long trainingId, Long questionId, User author, String content) {
		this.content = content;
		this.trainingId = trainingId;
		this.questionId = questionId;
		this.author = author;
		createdAt = new Date();
		state = State.NEW;
	}
	
	private AnswerDTO toDto() {
		return new AnswerDTO(content, author.getNickname(), createdAt);
	}
}
