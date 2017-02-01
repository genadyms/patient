package com.gmail.genadyms.web;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

public class Patients implements EntryPoint {

	@Override
	public void onModuleLoad() {
		PatientsServiceAsync rpcService = GWT.create(PatientsService.class);
		HandlerManager eventBus = new HandlerManager(null);
		AppController appController = new AppController(rpcService, eventBus);
		appController.go(RootPanel.get());

	}

}
