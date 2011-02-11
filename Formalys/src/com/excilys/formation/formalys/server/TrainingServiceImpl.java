package com.excilys.formation.formalys.server;

import java.util.List;

import javax.jdo.PersistenceManager;

import com.excilys.formation.formalys.client.TrainingService;
import com.excilys.formation.formalys.server.domain.Answer;
import com.excilys.formation.formalys.server.domain.PMF;
import com.excilys.formation.formalys.server.domain.Question;
import com.excilys.formation.formalys.server.domain.Training;
import com.excilys.formation.formalys.shared.AnswerDTO;
import com.excilys.formation.formalys.shared.FieldVerifier;
import com.excilys.formation.formalys.shared.QuestionDTO;
import com.excilys.formation.formalys.shared.TrainingDTO;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class TrainingServiceImpl extends RemoteServiceServlet implements TrainingService {

	public void createTraining(String name) {

		if (FieldVerifier.emptyContent(name)) {
			throw new IllegalArgumentException("The training name should not be empty");
		}

		name = escapeHtml(name);

		User currentUser = getCurrentUser();

		PersistenceManager pm = PMF.getPersistenceManager();
		try {
			Training.create(pm, currentUser, name);
		} finally {
			pm.close();
		}
	}

	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	private User getCurrentUser() {
		UserService userService = UserServiceFactory.getUserService();
		return userService.getCurrentUser();
	}

	@Override
	public List<TrainingDTO> getTrainings() {
		PersistenceManager pm = PMF.getPersistenceManager();
		try {
			return Training.getAsDtoAllOrderedByCreatedAt(pm);
		} finally {
			pm.close();
		}
	}

	@Override
	public void createQuestion(Long trainingId, String content) {
		if (FieldVerifier.emptyContent(content)) {
			throw new IllegalArgumentException("The question content should not be empty");
		}

		content = escapeHtml(content);

		User currentUser = getCurrentUser();

		PersistenceManager pm = PMF.getPersistenceManager();
		try {
			// TODO check that it throws an exception if unknown id
			Training training = pm.getObjectById(Training.class, trainingId);

			if (training.mayCreateQuestions()) {
				Question.create(pm, training, currentUser, content);
			} else {
				throw new IllegalStateException("No question can be created for this training");
			}
		} finally {
			pm.close();
		}
	}

	@Override
	public List<QuestionDTO> getQuestions(Long trainingId) {
		PersistenceManager pm = PMF.getPersistenceManager();
		User currentUser = getCurrentUser();
		try {
			return Question.getAsDtoByTrainingIdOrderedByCreatedAt(pm, trainingId, currentUser);
		} finally {
			pm.close();
		}
	}

	@Override
	public void createAnswer(Long questionId, String content) {

		if (FieldVerifier.emptyContent(content)) {
			throw new IllegalArgumentException("The answer content should not be empty");
		}

		content = escapeHtml(content);

		User currentUser = getCurrentUser();
		PersistenceManager pm = PMF.getPersistenceManager();
		try {
			// TODO check that it throws an exception if unknown id
			Question question = pm.getObjectById(Question.class, questionId);
			Training training = question.getTraining(pm);
			if (training.mayAnswerQuestions()) {
				if (Answer.notAnsweredYet(pm, questionId, currentUser)) {
					Answer.create(pm, question, currentUser, content);
				} else {
					throw new IllegalStateException("Question already answered");
				}
			} else {
				throw new IllegalStateException("No answer can be added for this training");
			}

		} finally {
			pm.close();
		}
	}

	@Override
	public List<AnswerDTO> getAnswers(Long questionId) {
		PersistenceManager pm = PMF.getPersistenceManager();
		try {
			// TODO check that it throws an exception if unknown id
			Question question = pm.getObjectById(Question.class, questionId);
			Training training = question.getTraining(pm);
			if (training.mayReviewAnswers()) {
				return Answer.getAsDtoByQuestionIdOrderedByCreatedAt(pm, questionId);
			} else {
				throw new IllegalStateException("Answers cannot be reviewed");
			}
		} finally {
			pm.close();
		}
	}
}
