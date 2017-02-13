package com.gmail.genadyms.web.presenter;

import com.gmail.genadyms.shared.dto.PatientDTO;
import com.gmail.genadyms.web.event.EditPatientCancelledEvent;
import com.gmail.genadyms.web.service.PatientServiceAsync;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Window;

import java.util.Date;

public class EditPatientPresenter implements Presenter {

    public interface Display {

        HasClickHandlers getSaveButton();

        HasClickHandlers getCancelButton();

        HasValue<String> getFirstName();

        HasValue<String> getLastName();

        HasValue<String> getAddress();

        HasValue<String> getDiagnosis();

        Widget asWidget();

        HasValueChangeHandlers getComingDate();

        void setComingDate(Date date);

        HasValueChangeHandlers getLeavingDate();

        void setLeavingDate(Date date);
    }

    private PatientDTO patient;
    private final PatientServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    public EditPatientPresenter(PatientServiceAsync rpcService, HandlerManager eventBus, Display display) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.patient = new PatientDTO();
        this.display = display;
        bind();
    }

    public EditPatientPresenter(PatientServiceAsync rpcService, HandlerManager eventBus, Display display, Long id) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = display;
        bind();

        rpcService.getPatient(id, new AsyncCallback<PatientDTO>() {
            public void onSuccess(PatientDTO result) {
                patient = result;
                EditPatientPresenter.this.display.getFirstName().setValue(patient.getFirstName());
                EditPatientPresenter.this.display.getLastName().setValue(patient.getLastName());
                EditPatientPresenter.this.display.getAddress().setValue(patient.getAddress());

            }

            public void onFailure(Throwable caught) {
                Window.alert("Error retrieving contact");
            }
        });

    }

    public void bind() {
        this.display.getSaveButton().addClickHandler(new ClickHandler() {


            public void onClick(ClickEvent event) {
                Window.alert("Edit patient ok");
                doSave();
            }
        });

        this.display.getCancelButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Window.alert("Edit patient cancelled");
                eventBus.fireEvent(new EditPatientCancelledEvent());
            }
        });


        this.display.getComingDate().addValueChangeHandler(new ValueChangeHandler<Date>() {
            public void onValueChange(ValueChangeEvent<Date> event) {
                Date date = event.getValue();
                patient.setComingDate(date);
                display.setComingDate(date);
            }
        });

        this.display.getLeavingDate().addValueChangeHandler(new ValueChangeHandler<Date>() {
            public void onValueChange(ValueChangeEvent<Date> event) {
                Date date = event.getValue();
                patient.setLeavingDate(date);
                display.setLeavingDate(date);
            }
        });

        Window.alert("Edit patient" + patient);
    }

    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
    }

    private void doSave() {
        patient.setFirstName(display.getFirstName().getValue());
        patient.setLastName(display.getLastName().getValue());
        patient.setAddress(display.getAddress().getValue());
        patient.setDiagnosis(display.getDiagnosis().getValue());

        if (null != patient.getId()) {
            rpcService.updatePatient(patient, new AsyncCallback<PatientDTO>() {
                @Override
                public void onFailure(Throwable caught) {
                    Window.alert("Error updating patient");
                }

                @Override
                public void onSuccess(PatientDTO result) {
                    Window.alert("Update patient!");
                }
            });
        } else {
            rpcService.addPatient(patient, new AsyncCallback<PatientDTO>() {
                public void onSuccess(PatientDTO result) {

                    Window.alert("Save patient!");
                }

                public void onFailure(Throwable caught) {

                    Window.alert("Error save patient");
                }
            });
        }
    }

}
