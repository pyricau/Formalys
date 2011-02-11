package com.excilys.formation.formalys.client;

import com.excilys.formation.formalys.client.ui.GoodbyeView;
import com.excilys.formation.formalys.client.ui.GoodbyeViewImpl;
import com.excilys.formation.formalys.client.ui.HelloView;
import com.excilys.formation.formalys.client.ui.HelloViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

public class ClientFactoryImpl implements ClientFactory
{
	private static final EventBus eventBus = new SimpleEventBus();
	private static final PlaceController placeController = new PlaceController(eventBus);
	private static final HelloView helloView = new HelloViewImpl();
	private static final GoodbyeView goodbyeView = new GoodbyeViewImpl();
	private static final TrainingServiceAsync trainingService = GWT.create(TrainingService.class);

	@Override
	public EventBus getEventBus()
	{
		return eventBus;
	}

	@Override
	public HelloView getHelloView()
	{
		return helloView;
	}

	@Override
	public PlaceController getPlaceController()
	{
		return placeController;
	}

	@Override
	public GoodbyeView getGoodbyeView()
	{
		return goodbyeView;
	}

	@Override
	public TrainingServiceAsync getTrainingService() {
		return trainingService;
	}

}
