package com.gmail.genadyms.web.presenter;

import com.gmail.genadyms.shared.PatientDTO;
import com.gmail.genadyms.web.PatientsServiceAsync;
import com.gmail.genadyms.web.event.PatientUpdatedEvent;
import com.gmail.genadyms.web.event.EditPatientCancelledEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Window;

public class EditPatientPresenter implements Presenter {
    public interface Display {
        HasClickHandlers getSaveButton();

        HasClickHandlers getCancelButton();

        HasValue<String> getFirstName();

        HasValue<String> getLastName();

        HasValue<String> getEmailAddress();

        Widget asWidget();
    }

    private PatientDTO contact;
    private final PatientsServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    public EditPatientPresenter(PatientsServiceAsync rpcService, HandlerManager eventBus, Display display) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.contact = new PatientDTO();
        this.display = display;
        bind();
    }

    public EditPatientPresenter(PatientsServiceAsync rpcService, HandlerManager eventBus, Display display, String id) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = display;
        bind();

        rpcService.getPatient(id, new AsyncCallback<PatientDTO>() {
            public void onSuccess(PatientDTO result) {
                contact = result;
                EditPatientPresenter.this.display.getFirstName().setValue(contact.getFirstName());
                EditPatientPresenter.this.display.getLastName().setValue(contact.getLastName());
                EditPatientPresenter.this.display.getEmailAddress().setValue(contact.getEmailAddress());
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error retrieving contact");
            }
        });

    }

    public void bind() {
        this.display.getSaveButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                doSave();
            }
        });

        this.display.getCancelButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new EditPatientCancelledEvent());
            }
        });
    }

    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
    }

    private void doSave() {
        contact.setFirstName(display.getFirstName().getValue());
        contact.setLastName(display.getLastName().getValue());
        contact.setEmailAddress(display.getEmailAddress().getValue());

        rpcService.updatePatient(contact, new AsyncCallback<PatientDTO>() {
            public void onSuccess(PatientDTO result) {
                eventBus.fireEvent(new PatientUpdatedEvent(result));
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error updating contact");
            }
        });
    }

}
