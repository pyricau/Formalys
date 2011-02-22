package com.excilys.formation.formalys.client;

import java.util.List;

import com.excilys.formation.formalys.shared.AnswerDTO;
import com.excilys.formation.formalys.shared.QuestionDTO;
import com.excilys.formation.formalys.shared.TrainingDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TrainingServiceAsync {
	void createTraining(String name, AsyncCallback<Void> callback);

	void getTrainings(AsyncCallback<List<TrainingDTO>> callback);

	void createQuestion(Long trainingId, String content, AsyncCallback<Void> callback);

	void getQuestions(Long trainingId, AsyncCallback<List<QuestionDTO>> callback);

	void getAnswers(Long questionId, AsyncCallback<List<AnswerDTO>> callback);

	void createAnswer(Long questionId, String content, AsyncCallback<Void> callback);
}
