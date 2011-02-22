package com.excilys.formation.formalys.client;

import com.excilys.formation.formalys.client.ui.CreateTrainingView;
import com.excilys.formation.formalys.client.ui.ListTrainingsView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

public interface ClientFactory
{
	EventBus getEventBus();
	PlaceController getPlaceController();

	ListTrainingsView getListTrainingsView();
	CreateTrainingView getCreateTrainingView();
	
	TrainingServiceAsync getTrainingService();
	
}
