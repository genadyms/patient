package com.gmail.genadyms.web.presenter;

import com.gmail.genadyms.shared.dto.PatientDTO;
import com.gmail.genadyms.shared.dto.WardDTO;
import com.gmail.genadyms.web.event.EditPatientCancelledEvent;
import com.gmail.genadyms.web.service.PatientServiceAsync;
import com.gmail.genadyms.web.service.WardServiceAsync;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
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
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error retrieving contact");
			}
		});
	}

	public void bind() {
		rpcWardService.getFreeWards(new AsyncCallback<List<WardDTO>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failure eward async!!!");
			}

			@Override
			public void onSuccess(List<WardDTO> result) {
				ListBox wardsListBox = EditPatientPresenter.this.display.getWardsListBox();
				wardsListBox.clear();
				int numberWard = -1;
				for (WardDTO dto : result) {
					String item = String.valueOf(dto.getNumberWard());
					wardsListBox.addItem(item);
					if ((numberWard == -1) && (null != patient.getNumberWard())) {
						if (patient.getNumberWard() == dto.getNumberWard()) {
							numberWard = wardsListBox.getItemCount() - 1;
							wardsListBox.setItemSelected(numberWard, true);
						}
					}
				}
			}
		});

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

//		 this.display.getWardsListBox().addChangeHandler(new ChangeHandler() {
//		 @Override
//		 public void onChange(ChangeEvent event) {
//		 Window.alert(event.getRelativeElement().getClass().getName());
//		 }
//		 });
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
		patient.setNumberWard(Integer.valueOf(display.getWardsListBox().getSelectedValue()));

		rpcPatientService.updatePatient(patient, new AsyncCallback<PatientDTO>() {
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
