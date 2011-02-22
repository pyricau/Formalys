package com.excilys.formation.formalys.client.ui;

import java.util.List;

import com.excilys.formation.formalys.shared.TrainingDTO;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface ListTrainingsView extends IsWidget
{
	void setPresenter(Presenter listener);

	public interface Presenter
	{
		void goTo(Place place);
	}

	void showLoading();
	
	void showLoadingError(Throwable caught);
	
	void showTrainings(List<TrainingDTO> trainings);
}