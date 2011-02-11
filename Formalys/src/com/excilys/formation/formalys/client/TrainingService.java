package com.excilys.formation.formalys.client;

import java.util.List;

import com.excilys.formation.formalys.shared.AnswerDTO;
import com.excilys.formation.formalys.shared.QuestionDTO;
import com.excilys.formation.formalys.shared.TrainingDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("training")
public interface TrainingService extends RemoteService {

	void createTraining(String name);

	List<TrainingDTO> getTrainings();

	void createQuestion(Long trainingId, String content);

	List<QuestionDTO> getQuestions(Long trainingId);
	
	void createAnswer(Long questionId, String content);
	
	List<AnswerDTO> getAnswers(Long questionId);
	
}
