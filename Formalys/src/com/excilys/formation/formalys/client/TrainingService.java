package com.excilys.formation.formalys.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("training")
public interface TrainingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
}
