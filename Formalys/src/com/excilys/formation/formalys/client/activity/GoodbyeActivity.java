package com.excilys.formation.formalys.client.activity;

import com.excilys.formation.formalys.client.ClientFactory;
import com.excilys.formation.formalys.client.place.GoodbyePlace;
import com.excilys.formation.formalys.client.ui.GoodbyeView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class GoodbyeActivity extends AbstractActivity {
	private ClientFactory clientFactory;
	// Name that will be appended to "Good-bye, "
	private String name;

	public GoodbyeActivity(GoodbyePlace place, ClientFactory clientFactory) {
		this.name = place.getGoodbyeName();
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		GoodbyeView goodbyeView = clientFactory.getGoodbyeView();
		goodbyeView.setName(name);
		containerWidget.setWidget(goodbyeView.asWidget());
	}
}