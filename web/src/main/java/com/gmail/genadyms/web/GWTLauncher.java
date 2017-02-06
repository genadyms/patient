package com.gmail.genadyms.web;

import com.gmail.genadyms.web.service.PatientService;
import com.gmail.genadyms.web.service.PatientServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

public class GWTLauncher implements EntryPoint {

    @Override
    public void onModuleLoad() {
        PatientServiceAsync rpcService = GWT.create(PatientService.class);
        HandlerManager eventBus = new HandlerManager(null);
        AppController appController = new AppController(rpcService, eventBus);
        appController.go(RootPanel.get());
    }
}
