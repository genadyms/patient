package com.gmail.genadyms.web.presenter;

import com.gmail.genadyms.shared.dto.PatientDTO;
import com.gmail.genadyms.shared.dto.WardDTO;
import com.gmail.genadyms.web.event.EditPatientCancelledEvent;
import com.gmail.genadyms.web.service.PatientServiceAsync;
import com.gmail.genadyms.web.service.WardServiceAsync;
import com.gmail.genadyms.web.view.ConstantsValue;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.Window;

import java.util.*;

public class EditPatientPresenter implements Presenter {

    public interface Display {

        HasClickHandlers getSaveButton();

        HasClickHandlers getCancelButton();

        HasValue<String> getFirstName();

        HasValue<String> getLastName();

        HasValue<String> getAddress();

        HasValue<String> getDiagnosis();

        Widget asWidget();

        HasValue<Date> getComingDateBox();

        HasValue<Date> getLeavingDateBox();

        ListBox getWardsListBox();
    }

    private PatientDTO patient;
    private final PatientServiceAsync rpcPatientService;
    private final WardServiceAsync rpcWardService;
    private final HandlerManager eventBus;
    private final Display display;

    public EditPatientPresenter(PatientServiceAsync rpcService, WardServiceAsync rpcWardService,
                                HandlerManager eventBus, Display display) {
        this.rpcPatientService = rpcService;
        this.rpcWardService = rpcWardService;
        this.eventBus = eventBus;
        this.patient = new PatientDTO();
        this.display = display;
        bind();
    }

    public EditPatientPresenter(PatientServiceAsync rpcPatientService, WardServiceAsync rpcWardService,
                                HandlerManager eventBus, Display display, Long id) {

        this(rpcPatientService, rpcWardService, eventBus, display);

        rpcPatientService.getPatient(id, new AsyncCallback<PatientDTO>() {
            public void onSuccess(PatientDTO result) {
                patient = result;
                EditPatientPresenter.this.display.getFirstName().setValue(patient.getFirstName());
                EditPatientPresenter.this.display.getLastName().setValue(patient.getLastName());
                EditPatientPresenter.this.display.getAddress().setValue(patient.getAddress());
                EditPatientPresenter.this.display.getDiagnosis().setValue(patient.getDiagnosis());
                EditPatientPresenter.this.display.getComingDateBox().setValue(patient.getComingDate());
                EditPatientPresenter.this.display.getLeavingDateBox().setValue(patient.getLeavingDate());

                ListBox wards = EditPatientPresenter.this.display.getWardsListBox();
                for (int i = 0; i < wards.getItemCount(); i++) {
                    if (Integer.valueOf(wards.getItemText(i)) == (patient.getNumberWard())) {
                        wards.setItemSelected(i, true);
                        break;
                    }
                }
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error retrieving contact");
            }
        });

        ListBox wards = EditPatientPresenter.this.display.getWardsListBox();
        for (int i = 0; i < wards.getItemCount(); i++) {
            if (Integer.valueOf(wards.getItemText(i)) == (patient.getNumberWard())) {
                wards.setItemSelected(i, true);
                break;
            }
        }
    }

    private void prepareWards() {
        rpcWardService.getFreeWards(new AsyncCallback<Set<WardDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Failure ward async!!!");
            }

            @Override
            public void onSuccess(Set<WardDTO> result) {
                ListBox wardsListBox = EditPatientPresenter.this.display.getWardsListBox();
                wardsListBox.clear();
                boolean isNotSelectedWard = null == patient.getId() ? false : true;
                for (WardDTO dto : result) {
                    String item = String.valueOf(dto.getNumberWard());
                    wardsListBox.addItem(item);
                    if (isNotSelectedWard) {
                        if (dto.getNumberWard() == patient.getNumberWard()) {
                            wardsListBox.setSelectedIndex(wardsListBox.getItemCount() - 1);
                            isNotSelectedWard = false;
                        }
                    }
                }
                if (isNotSelectedWard && null != patient.getId()) {
                    wardsListBox.addItem(String.valueOf(patient.getNumberWard()));
                    wardsListBox.setSelectedIndex(wardsListBox.getItemCount() - 1);
                }
            }
        });

    }

    public void bind() {
        this.display.getSaveButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {

                patient.setFirstName(display.getFirstName().getValue());
                patient.setLastName(display.getLastName().getValue());
                patient.setAddress(display.getAddress().getValue());
                patient.setDiagnosis(display.getDiagnosis().getValue());
                patient.setNumberWard(Integer.valueOf(display.getWardsListBox().getSelectedValue()));

                String errorMsg = validate(patient);
                if (errorMsg.isEmpty()) {
                    doSave();
                } else {
                    Window.alert(errorMsg);
                }

            }
        });

        this.display.getCancelButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new EditPatientCancelledEvent());
            }
        });
        this.display.getComingDateBox().addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                Date date = event.getValue();
                patient.setComingDate(date);
            }
        });
        this.display.getLeavingDateBox().addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                Date date = event.getValue();
                patient.setLeavingDate(date);
            }
        });
    }

    private String validate(PatientDTO patient) {

        StringBuffer errorMessage= new StringBuffer();

        if (patient.getFirstName().length()<3||patient.getFirstName().length()>30) {
            errorMessage.append(ConstantsValue.FIRST_NAME).append(ConstantsValue.MSG_VALIDATE).append(ConstantsValue.NEW_LINE);
        }

        if (patient.getLastName().length()<3||patient.getLastName().length()>30) {
            errorMessage.append(ConstantsValue.LAST_NAME).append(ConstantsValue.MSG_VALIDATE).append(ConstantsValue.NEW_LINE);
        }

        if (patient.getAddress().length()<3||patient.getAddress().length()>30) {
            errorMessage.append(ConstantsValue.ADDRESS).append(ConstantsValue.MSG_VALIDATE).append(ConstantsValue.NEW_LINE);
        }


        if (patient.getDiagnosis().length()<3||patient.getDiagnosis().length()>30) {
            errorMessage.append(ConstantsValue.DIAGNOSIS).append(ConstantsValue.MSG_VALIDATE).append(ConstantsValue.NEW_LINE);
        }

        if (null!=patient.getLeavingDate()&&null!=patient.getComingDate()) {
            if(patient.getComingDate().getTime()>patient.getLeavingDate().getTime()) {
                errorMessage.append(ConstantsValue.LEAVING_DATE).append(ConstantsValue.MSG_VALIDATE).append(ConstantsValue.NEW_LINE);
            }
        }
        return errorMessage.toString();
    }

    public void go(final HasWidgets container) {
        prepareWards();
        container.clear();
        container.add(display.asWidget());
    }

    private void doSave() {
        rpcPatientService.updateOrSavePatient(patient, new AsyncCallback<PatientDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error updating patient");
            }

            @Override
            public void onSuccess(PatientDTO result) {

                eventBus.fireEvent(new EditPatientCancelledEvent());
            }
        });
    }
}
