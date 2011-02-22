package com.excilys.formation.formalys.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ListQuestionsPlace extends Place {
	private final Long trainingId;

	public ListQuestionsPlace(Long trainingId) {
		this.trainingId = trainingId;
	}

	public Long getTrainingId() {
		return trainingId;
	}

	public static class Tokenizer implements PlaceTokenizer<ListQuestionsPlace> {
		@Override
		public String getToken(ListQuestionsPlace place) {
			return place.getTrainingId().toString();
		}

		@Override
		public ListQuestionsPlace getPlace(String token) {
			long trainingId = Long.parseLong(token);
			return new ListQuestionsPlace(trainingId);
		}
	}

}
