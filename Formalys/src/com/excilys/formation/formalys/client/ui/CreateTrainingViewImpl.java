package com.excilys.formation.formalys.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class CreateTrainingViewImpl extends Composite implements CreateTrainingView
{
	private static MyBinder uiBinder = GWT.create(MyBinder.class);

	interface MyBinder extends UiBinder<Widget, CreateTrainingViewImpl>
	{
	}
	
	private Presenter listener;
	
	@UiField
	TextBox name;
	
	@UiField
	Label message;
	
	@UiField
	InlineLabel emptyName;
	
	@UiField
	HTMLPanel createForm;

	public CreateTrainingViewImpl()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

	@Override
	public void showForm() {
		name.setText("");
		
		createForm.setVisible(true);
		
		message.setVisible(false);
		emptyName.setVisible(false);
	}

	@Override
	public void showCreateError(Throwable caught) {
		message.setText("Une erreur est apparue : "+caught.toString());
		
		createForm.setVisible(true);
		
		message.setVisible(true);
		emptyName.setVisible(false);
	}


	@Override
	public void showCreating(String trainingName) {
		
		name.setText("");
		message.setText("Création de la formation "+trainingName+" en cours");
		
		createForm.setVisible(false);
		message.setVisible(true);
		emptyName.setVisible(false);
		
	}
	
	@UiHandler("name")
	void onKeuDown(KeyDownEvent e) {
		if(e.getNativeKeyCode()==KeyCodes.KEY_ENTER) {
			create();
		}
	}
	
	@UiHandler("create")
	void onCreate(ClickEvent click) {
		create();
	}
	
	private void create() {
		listener.createTraining(name.getText());
	}

	@Override
	public void showEmptyName() {
		emptyName.setVisible(true);		
	}
}
