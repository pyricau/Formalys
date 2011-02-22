package com.excilys.formation.formalys.client.activity;

import com.excilys.formation.formalys.client.ClientFactory;
import com.excilys.formation.formalys.client.TrainingServiceAsync;
import com.excilys.formation.formalys.client.place.ListTrainingsPlace;
import com.excilys.formation.formalys.client.ui.CreateTrainingView;
import com.excilys.formation.formalys.shared.FieldVerifier;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class CreateTrainingActivity extends AbstractActivity implements CreateTrainingView.Presenter {

	private ClientFactory clientFactory;
	private CreateTrainingView view;

	public CreateTrainingActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget container, EventBus eventBus) {
		view = clientFactory.getCreateTrainingView();
		view.setPresenter(this);

		view.showForm();

		container.setWidget(view.asWidget());
	}

	@Override
	public void createTraining(String name) {

		if (FieldVerifier.emptyContent(name)) {
			view.showEmptyName();
		} else {

			TrainingServiceAsync trainingService = clientFactory.getTrainingService();

			view.showCreating(name);
			trainingService.createTraining(name, new AsyncCallback<Void>() {
				@Override
				public void onSuccess(Void result) {
					PlaceController placeController = clientFactory.getPlaceController();
					placeController.goTo(new ListTrainingsPlace());
				}

				@Override
				public void onFailure(Throwable caught) {
					view.showCreateError(caught);
				}
			});
		}
	}
}
