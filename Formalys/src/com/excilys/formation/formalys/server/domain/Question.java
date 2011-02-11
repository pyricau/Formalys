package com.excilys.formation.formalys.server.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.excilys.formation.formalys.shared.QuestionDTO;
import com.google.appengine.api.users.User;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@SuppressWarnings("unused")
public class Question {

	public static Map<Long, Integer> countByTrainingId(PersistenceManager pm) {

		Query query = pm.newQuery(Question.class);
		@SuppressWarnings("unchecked")
		List<Question> questions = (List<Question>) query.execute();

		Map<Long, Integer> countsByTrainingId = new HashMap<Long, Integer>();

		for (Question question : questions) {
			Integer currentCount = countsByTrainingId.get(question.trainingId);
			if (currentCount == null) {
				currentCount = 0;
			}
			Integer newCount = currentCount + 1;
			countsByTrainingId.put(question.trainingId, newCount);
		}

		return countsByTrainingId;
	}

	public static List<QuestionDTO> getAsDtoByTrainingIdOrderedByCreatedAt(PersistenceManager pm, Long trainingId, User user) {
		List<Question> questions = getByTrainingIdOrderedByCreatedAt(pm, trainingId);

		List<Long> userAnsweredQuestionIds = Answer.getAnsweredQuestionIdsByTrainingIdAndUser(pm, trainingId, user);

		return toDto(questions, userAnsweredQuestionIds);
	}

	public static List<Question> getByTrainingIdOrderedByCreatedAt(PersistenceManager pm, Long trainingId) {
		Query query = pm.newQuery(Question.class, "trainingId = trainingIdParam");
		query.declareParameters("Long trainingIdParam");
		query.setOrdering("createdAt desc");
		@SuppressWarnings("unchecked")
		List<Question> questions = (List<Question>) query.execute(trainingId);
		return questions;
	}

	public static List<QuestionDTO> toDto(List<Question> questions, List<Long> userAnsweredQuestionIds) {
		List<QuestionDTO> questionDTOs = new ArrayList<QuestionDTO>();
		for (Question question : questions) {
			boolean hasAnswered = userAnsweredQuestionIds.contains(question.id);
			questionDTOs.add(question.toDto(hasAnswered));
		}
		return questionDTOs;
	}

	public static void create(PersistenceManager pm, Training training, User questionAuthor, String content) {
		Question question = training.newQuestion(questionAuthor, content);
		pm.makePersistent(question);
	}

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
	private Long trainingId;

	Question(Long trainingId, User author, String content) {
		this.trainingId = trainingId;
		this.author = author;
		this.content = content;
		createdAt = new Date();
		state = State.NEW;
	}

	public Answer newAnswer(User answerAuthor, String answerContent) {
		return new Answer(trainingId, id, answerAuthor, answerContent);
	}

	private QuestionDTO toDto(boolean hasAnswered) {
		return new QuestionDTO(id, content, author.getNickname(), createdAt, hasAnswered);
	}

	public Training getTraining(PersistenceManager pm) {
		return pm.getObjectById(Training.class, trainingId);
	}

}
