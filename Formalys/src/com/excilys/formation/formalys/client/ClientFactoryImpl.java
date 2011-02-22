package com.excilys.formation.formalys.client;

import com.excilys.formation.formalys.client.ui.CreateTrainingView;
import com.excilys.formation.formalys.client.ui.CreateTrainingViewImpl;
import com.excilys.formation.formalys.client.ui.ListTrainingsView;
import com.excilys.formation.formalys.client.ui.ListTrainingsViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

public class ClientFactoryImpl implements ClientFactory
{
	private static final EventBus eventBus = new SimpleEventBus();
	private static final PlaceController placeController = new PlaceController(eventBus);
	private static final TrainingServiceAsync trainingService = GWT.create(TrainingService.class);

	@Override
	public EventBus getEventBus()
	{
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController()
	{
		return placeController;
	}

	@Override
	public TrainingServiceAsync getTrainingService() {
		return trainingService;
	}

	@Override
	public ListTrainingsView getListTrainingsView() {
		return new ListTrainingsViewImpl();
	}

	@Override
	public CreateTrainingView getCreateTrainingView() {
		return new CreateTrainingViewImpl();
	}

}
