package com.gmail.genadyms.web;

import com.gmail.genadyms.web.presenter.PatientsPresenter;
import com.gmail.genadyms.web.presenter.Presenter;
import com.gmail.genadyms.web.view.PatientsView;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
    private final HandlerManager eventBus;
    private final GreetingServiceAsync rpcService;
    private HasWidgets container;

    public AppController(GreetingServiceAsync rpcService, HandlerManager eventBus) {
        this.eventBus = eventBus;
        this.rpcService = rpcService;
        bind();
    }

    private void bind() {
        History.addValueChangeHandler(this);
    }

    private void doAddNewContact() {
        History.newItem("add");
    }


    @Override
    public void go(HasWidgets container) {
        this.container = container;

        if ("".equals(History.getToken())) {
            History.newItem("list");
        } else {
            History.fireCurrentHistoryState();
        }
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();
        Presenter presenter = new PatientsPresenter(rpcService, eventBus, new PatientsView());
    }
}
