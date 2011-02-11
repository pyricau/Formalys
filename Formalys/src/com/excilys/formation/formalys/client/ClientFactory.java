package com.excilys.formation.formalys.client;

import com.excilys.formation.formalys.client.ui.GoodbyeView;
import com.excilys.formation.formalys.client.ui.HelloView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

public interface ClientFactory
{
	EventBus getEventBus();
	PlaceController getPlaceController();
	HelloView getHelloView();
	GoodbyeView getGoodbyeView();
	TrainingServiceAsync getTrainingService();
	
}
