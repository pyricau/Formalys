package com.excilys.formation.formalys.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class CreateTrainingPlace extends Place
{
	
	public static class Tokenizer implements PlaceTokenizer<CreateTrainingPlace>
	{
		@Override
		public String getToken(CreateTrainingPlace place)
		{
			return "";
		}

		@Override
		public CreateTrainingPlace getPlace(String token)
		{
			return new CreateTrainingPlace();
		}
	}
	
}
