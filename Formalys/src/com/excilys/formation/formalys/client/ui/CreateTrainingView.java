package com.excilys.formation.formalys.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

public interface CreateTrainingView extends IsWidget
{
	
	void setPresenter(Presenter listener);
	
	public interface Presenter
	{
		void createTraining(String name);
	}

	void showForm();

	void showCreateError(Throwable caught);

	void showCreating(String name);

	void showEmptyName();
	
}