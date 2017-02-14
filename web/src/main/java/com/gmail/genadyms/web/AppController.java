package com.gmail.genadyms.web;

import com.gmail.genadyms.web.event.*;
import com.gmail.genadyms.web.presenter.PatientsPresenter;
import com.gmail.genadyms.web.presenter.EditPatientPresenter;
import com.gmail.genadyms.web.presenter.Presenter;
import com.gmail.genadyms.web.service.PatientServiceAsync;
import com.gmail.genadyms.web.service.WardServiceAsync;
import com.gmail.genadyms.web.view.PatientsView;
import com.gmail.genadyms.web.view.EditPatientView;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
    private final HandlerManager eventBus;
    private final PatientServiceAsync rpcPatientService;
    private final WardServiceAsync rpcWardService;
    private HasWidgets container;

    public AppController(PatientServiceAsync rpcPatientService, WardServiceAsync rpcWardService, HandlerManager eventBus) {
        this.eventBus = eventBus;
        this.rpcPatientService = rpcPatientService;
        this.rpcWardService = rpcWardService;
        bind();
    }

    private void bind() {
        History.addValueChangeHandler(this);

        eventBus.addHandler(AddPatientEvent.TYPE,
                new AddPatientEventHandler() {
                    public void onAddPatient(AddPatientEvent event) {
                        doAddNewContact();
                    }
                });

        eventBus.addHandler(EditPatientEvent.TYPE,
                new EditPatientEventHandler() {
                    public void onEditPatient(EditPatientEvent event) {
                        doEditPatient(event.getId());
                    }
                });

        eventBus.addHandler(EditPatientCancelledEvent.TYPE,
                new EditPatientCancelledEventHandler() {
                    public void onEditPatientCancelled(EditPatientCancelledEvent event) {
                        doEditPatientCancelled();
                    }
                });

        eventBus.addHandler(PatientUpdatedEvent.TYPE,
                new PatientUpdatedEventHandler() {
                    public void onPatientUpdated(PatientUpdatedEvent event) {
                        doPatientUpdated();
                    }
                });
    }

    private void doAddNewContact() {
        History.newItem("add");
    }

    private void doEditPatient(Long id) {
        History.newItem("edit", false);
        Presenter presenter = new EditPatientPresenter(rpcPatientService, rpcWardService, eventBus, new EditPatientView(), id);
        presenter.go(container);
    }

    private void doEditPatientCancelled() {
        History.newItem("list");
    }

    private void doPatientUpdated() {
        History.newItem("list");
    }

    public void go(final HasWidgets container) {
        this.container = container;

        if ("".equals(History.getToken())) {
            History.newItem("list");
        } else {
            History.fireCurrentHistoryState();
        }
    }

    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();

        if (token != null) {
            Presenter presenter = null;

            if (token.equals("list")) {
                presenter = new PatientsPresenter(rpcPatientService, eventBus, new PatientsView());
            } else if (token.equals("add")) {
                presenter = new EditPatientPresenter(rpcPatientService, rpcWardService,eventBus, new EditPatientView());
            } else if (token.equals("edit")) {
                presenter = new EditPatientPresenter(rpcPatientService,  rpcWardService, eventBus, new EditPatientView());
            }

            if (presenter != null) {
                presenter.go(container);
            }
        }
    }
}
