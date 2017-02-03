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

		HasClickHandlers getDeleteButton();

		HasClickHandlers getList();

		void setData(List<String> data);

		int getClickedRow(ClickEvent event);

		List<Integer> getSelectedRows();

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

//		display.getDeleteButton().addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				deleteSelectedContacts();
//			}
//		});

//		display.getList().addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				int selectedRow = display.getClickedRow(event);
//
//				if (selectedRow >= 0) {
//					String id = patientsDTO.get(selectedRow).getId();
//					eventBus.fireEvent(new EditPatientEvent(id));
//				}
//			}
//		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
		fetchContactDetails();
	}

	public void sortContactDetails() {

		// Yes, we could use a more optimized method of sorting, but the
		// point is to create a test case that helps illustrate the higher
		// level concepts used when creating MVP-based applications.
		//
//		for (int i = 0; i < patientsDTO.size(); ++i) {
//			for (int j = 0; j < patientsDTO.size() - 1; ++j) {
//				if (patientsDTO.get(j).getDisplayName()
//						.compareToIgnoreCase(patientsDTO.get(j + 1).getDisplayName()) >= 0) {
//					PatientDetails tmp = patientsDTO.get(j);
//					patientsDTO.set(j, patientsDTO.get(j + 1));
//					patientsDTO.set(j + 1, tmp);
//				}
//			}
//		}
	}

//	public void setContactDetails(List<PatientDetails> contactDetails) {
//		this.patientsDTO = contactDetails;
//	}
//
//	public PatientDetails getContactDetail(int index) {
//		return patientsDTO.get(index);
//	}

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

//	private void deleteSelectedContacts() {
//		List<Integer> selectedRows = display.getSelectedRows();
//		ArrayList<String> ids = new ArrayList<String>();
//
//		for (int i = 0; i < selectedRows.size(); ++i) {
//			ids.add(patientsDTO.get(selectedRows.get(i)).getId());
//		}
//
//	}
}
