package com.gmail.genadyms.web.presenter;

import com.gmail.genadyms.shared.PatientDTO;
import com.gmail.genadyms.shared.PatientDetails;
import com.gmail.genadyms.web.PatientServiceAsync;
import com.gmail.genadyms.web.event.AddPatientEvent;
import com.gmail.genadyms.web.event.EditPatientEvent;
import com.gmail.genadyms.web.presenter.Presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

public class PatientsPresenter implements Presenter {

	private List<PatientDTO> patientsDTO;

	public interface Display {
		HasClickHandlers getAddButton();

		HasClickHandlers getList();

		void setData(List<String> data);

		int getClickedRow(ClickEvent event);

		Widget asWidget();
	}

	private final PatientServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public PatientsPresenter(PatientServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddPatientEvent());
			}
		});


		display.getList().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int selectedRow = display.getClickedRow(event);

				if (selectedRow >= 0) {
					Long id = patientsDTO.get(selectedRow).getId();
					eventBus.fireEvent(new EditPatientEvent(id));
				}
			}
		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
		fetchContactDetails();
	}

	private void fetchContactDetails() {
		rpcService.getPatients(new AsyncCallback<List<PatientDTO>>() {
			public void onSuccess(List<PatientDTO> result) {
				patientsDTO = result;
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(patientsDTO.get(i).getAddress());
				}

				display.setData(data);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error fetching contact details");
			}
		});
	}
}
