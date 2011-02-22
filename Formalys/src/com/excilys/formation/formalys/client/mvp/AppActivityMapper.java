package com.excilys.formation.formalys.client.mvp;

import com.excilys.formation.formalys.client.ClientFactory;
import com.excilys.formation.formalys.client.activity.CreateTrainingActivity;
import com.excilys.formation.formalys.client.activity.ListTrainingsActivity;
import com.excilys.formation.formalys.client.place.CreateTrainingPlace;
import com.excilys.formation.formalys.client.place.ListTrainingsPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class AppActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;

	/**
	 * AppActivityMapper associates each Place with its corresponding
	 * {@link Activity}
	 * 
	 * @param clientFactory
	 *            Factory to be passed to activities
	 */
	public AppActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof ListTrainingsPlace)
			return new ListTrainingsActivity(clientFactory);
		else if (place instanceof CreateTrainingPlace)
			return new CreateTrainingActivity(clientFactory);

		return null;
	}

}
