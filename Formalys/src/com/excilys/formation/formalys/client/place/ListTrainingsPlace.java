package com.excilys.formation.formalys.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ListTrainingsPlace extends Place
{
	
	public static class Tokenizer implements PlaceTokenizer<ListTrainingsPlace>
	{
		@Override
		public String getToken(ListTrainingsPlace place)
		{
			return "";
		}

		@Override
		public ListTrainingsPlace getPlace(String token)
		{
			return new ListTrainingsPlace();
		}
	}
	
}
