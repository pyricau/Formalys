package com.excilys.formation.formalys.client.ui;

import java.util.List;

import com.excilys.formation.formalys.client.place.CreateTrainingPlace;
import com.excilys.formation.formalys.client.place.ListQuestionsPlace;
import com.excilys.formation.formalys.shared.TrainingDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

public class ListTrainingsViewImpl extends Composite implements ListTrainingsView
{
	private static MyBinder uiBinder = GWT.create(MyBinder.class);

	interface MyBinder extends UiBinder<Widget, ListTrainingsViewImpl>
	{
	}
	
	@UiField
	FlowPanel trainingList;
	
	private Presenter listener;

	public ListTrainingsViewImpl()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter listener)
	{
		this.listener = listener;
	}

	@Override
	public void showLoading() {
		trainingList.clear();
		InlineLabel label = new InlineLabel("Chargement des formations en cours...");
		trainingList.add(label);
	}

	@Override
	public void showLoadingError(Throwable caught) {
		trainingList.clear();
		InlineLabel label = new InlineLabel("Une erreur est apparu au chargement des formations: "+caught.toString());
		trainingList.add(label);
	}

	@Override
	public void showTrainings(List<TrainingDTO> trainings) {
		trainingList.clear();
		for(final TrainingDTO training : trainings) {
			Anchor anchor = new Anchor(training.getName(), true);
			anchor.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					listener.goTo(new ListQuestionsPlace(training.getId()));
				}
			});
			InlineLabel label = new InlineLabel(" par " + training.getAuthorNickname());
			
			FlowPanel trainingPanel = new FlowPanel();
			trainingPanel.add(anchor);
			trainingPanel.add(label);
			trainingList.add(trainingPanel);
		}
	}
	
	@UiHandler("createButton")
	void onCreate(ClickEvent e) {
		listener.goTo(new CreateTrainingPlace());
	}
}
