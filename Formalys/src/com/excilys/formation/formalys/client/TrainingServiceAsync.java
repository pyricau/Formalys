package com.excilys.formation.formalys.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TrainingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
}
