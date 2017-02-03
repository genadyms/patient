package com.gmail.genadyms.web;

import com.gmail.genadyms.web.event.*;
import com.gmail.genadyms.web.presenter.PatientsPresenter;
import com.gmail.genadyms.web.presenter.EditPatientPresenter;
import com.gmail.genadyms.web.presenter.Presenter;
import com.gmail.genadyms.web.view.PatientsView;
import com.gmail.genadyms.web.view.EditPatientView;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
    private final HandlerManager eventBus;
    private final PatientServiceAsync rpcService;
    private HasWidgets container;

    public AppController(PatientServiceAsync rpcService, HandlerManager eventBus) {
        this.eventBus = eventBus;
        this.rpcService = rpcService;
        bind();
    }

    private void bind() {
        History.addValueChangeHandler(this);

        eventBus.addHandler(AddPatientEvent.TYPE,
                new AddPatientEventHandler() {
                    public void onAddContact(AddPatientEvent event) {
                        doAddNewContact();
                    }
                });

        eventBus.addHandler(EditPatientEvent.TYPE,
                new EditPatientEventHandler() {
                    public void onEditContact(EditPatientEvent event) {
                        doEditContact(event.getId());
                    }
                });

        eventBus.addHandler(EditPatientCancelledEvent.TYPE,
                new EditPatientCancelledEventHandler() {
                    public void onEditContactCancelled(EditPatientCancelledEvent event) {
                        doEditContactCancelled();
                    }
                });

        eventBus.addHandler(PatientUpdatedEvent.TYPE,
                new PatientUpdatedEventHandler() {
                    public void onContactUpdated(PatientUpdatedEvent event) {
                        doContactUpdated();
                    }
                });
    }

    private void doAddNewContact() {
        History.newItem("add");
    }

    private void doEditContact(Long id) {
        History.newItem("edit", false);
        Presenter presenter = new EditPatientPresenter(rpcService, eventBus, new EditPatientView(), id);
        presenter.go(container);
    }

    private void doEditContactCancelled() {
        History.newItem("list");
    }

    private void doContactUpdated() {
        History.newItem("list");
    }

    public void go(final HasWidgets container) {
        this.container = container;

        if ("".equals(History.getToken())) {
            History.newItem("list");
        }
        else {
            History.fireCurrentHistoryState();
        }
    }

    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();

        if (token != null) {
            Presenter presenter = null;

            if (token.equals("list")) {
                presenter = new PatientsPresenter(rpcService, eventBus, new PatientsView());
            }
            else if (token.equals("add")) {
                presenter = new EditPatientPresenter(rpcService, eventBus, new EditPatientView());
            }
            else if (token.equals("edit")) {
                presenter = new EditPatientPresenter(rpcService, eventBus, new EditPatientView());
            }

            if (presenter != null) {
                presenter.go(container);
            }
        }
    }
}
