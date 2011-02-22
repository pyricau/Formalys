package com.excilys.formation.formalys.client.activity;

import java.util.List;

import com.excilys.formation.formalys.client.ClientFactory;
import com.excilys.formation.formalys.client.TrainingServiceAsync;
import com.excilys.formation.formalys.client.ui.ListTrainingsView;
import com.excilys.formation.formalys.shared.TrainingDTO;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class ListTrainingsActivity extends AbstractActivity implements
ListTrainingsView.Presenter {

	private ClientFactory clientFactory;
	

	public ListTrainingsActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget container, EventBus eventBus) {
		ListTrainingsView view = clientFactory.getListTrainingsView();
		view.setPresenter(this);
		
		view.showLoading();
		
		container.setWidget(view.asWidget());
		
		loadTraining(view);
	}

	private void loadTraining(final ListTrainingsView view) {
		TrainingServiceAsync trainingService = clientFactory.getTrainingService();
		trainingService.getTrainings(new AsyncCallback<List<TrainingDTO>>() {
			@Override
			public void onSuccess(List<TrainingDTO> result) {
				view.showTrainings(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				view.showLoadingError(caught);
			}
		});
	}

	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}
}
