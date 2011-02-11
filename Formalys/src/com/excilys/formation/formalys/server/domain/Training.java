package com.excilys.formation.formalys.server.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.excilys.formation.formalys.shared.QuestionState;
import com.excilys.formation.formalys.shared.TrainingDTO;
import com.google.appengine.api.users.User;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Training {

	public static List<TrainingDTO> getAsDtoAllOrderedByCreatedAt(PersistenceManager pm) {
		List<Training> trainings = getAllOrderedByCreatedAt(pm);
		Map<Long, Integer> questionCountByTrainingId = Question.countByTrainingId(pm);
		return toDto(trainings, questionCountByTrainingId);
	}
	
	public static List<Training> getAllOrderedByCreatedAt(PersistenceManager pm) {
		Query query = pm.newQuery(Training.class);
		query.setOrdering("createdAt desc");
		@SuppressWarnings("unchecked")
		List<Training> trainings = (List<Training>) query.execute();
		return trainings;
	}
	
	public static List<TrainingDTO> toDto(List<Training> trainings, Map<Long, Integer> questionCountByTrainingId) {
		List<TrainingDTO> trainingDTOs = new ArrayList<TrainingDTO>();
		for(Training training : trainings) {
			Integer questionCount = questionCountByTrainingId.get(training.id);
			if (questionCount==null) {
				questionCount = 0;
			}
			trainingDTOs.add(training.toDto(questionCount));
		}
		return trainingDTOs;
	}
	
	public static void create(PersistenceManager pm, User author, String name) {
		Training training = new Training(author, name);
		pm.makePersistent(training);
	}
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String name;

	@Persistent
	private User author;

	@Persistent
	private Date createdAt;

	@Persistent
	private QuestionState questionState;

	public Training(User author, String name) {
		this.name = name;
		this.author = author;
		createdAt = new Date();
		questionState = QuestionState.CREATE_QUESTIONS;
	}
	
	public Question newQuestion(User questionAuthor, String content) {
		return new Question(id, questionAuthor, content);
	}
	
	public TrainingDTO toDto(int questionCount) {
		return new TrainingDTO(id, name, author.getNickname(), createdAt, questionState, questionCount);
	}
	
	public boolean mayCreateQuestions() {
		return questionState == QuestionState.CREATE_QUESTIONS;
	}
	
	public boolean mayAnswerQuestions() {
		return questionState == QuestionState.ANSWER_QUESTIONS;
	}

	public boolean mayReviewAnswers() {
		return questionState == QuestionState.REVIEW_ANSWERS;
	}

}
