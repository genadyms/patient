package com.gmail.genadyms.web.presenter;

import com.gmail.genadyms.shared.dto.PatientDTO;
import com.gmail.genadyms.web.event.AddPatientEvent;
import com.gmail.genadyms.web.event.EditPatientEvent;
import com.gmail.genadyms.web.presenter.Presenter;
import com.gmail.genadyms.web.service.PatientServiceAsync;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientsPresenter implements Presenter {

    public interface Display {
        HasClickHandlers getAddButton();

        HasClickHandlers getList();

        void setData(List<String> data);

        int getClickedRow(ClickEvent event);

        Widget asWidget();

        void setProvider(AsyncDataProvider<PatientDTO> provider, int size);

    }

    private final PatientServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;
    private final List<PatientDTO> patientsData = new ArrayList();

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

                // if (selectedRow >= 0) {
                // Long id = patientDTO.get(selectedRow).getId();
                // eventBus.fireEvent(new EditPatientEvent(id));
                // }
            }
        });
    }

    public void go(final HasWidgets container) {
        // bind();
        container.clear();
        container.add(display.asWidget());
//		fetchContactDetails();
//        for (int i = 0; i < 10; i++) {
//            PatientDTO p = new PatientDTO();
//            p.setFirstName("firstName " + i);
//            p.setLastName("lastName " + i);
//            p.setDiagnosis("diagnosis " + i);
//            p.setComingDate(new Date());
//            p.setLeavingDate(new Date());
//            p.setWard(Long.valueOf(20 + i));
//            patientsData.add(p);
//        }


        AsyncDataProvider<PatientDTO> provider = new AsyncDataProvider<PatientDTO>() {
            @Override
//			protected void onRangeChanged(HasData<PatientDTO> display) {
//				int start = display.getVisibleRange().getStart();
//				int end = start + display.getVisibleRange().getLength();
//				end = end >= patientsData.size() ? patientsData.size() : end;
//				List<PatientDTO> sub = patientsData.subList(start, end);
//				updateRowData(start, sub);
//			}
            protected void onRangeChanged(HasData<PatientDTO> display) {
                final int start = display.getVisibleRange().getStart();
                int length = display.getVisibleRange().getLength();
                AsyncCallback<List<PatientDTO>> callback = new AsyncCallback<List<PatientDTO>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(List<PatientDTO> result) {
                        updateRowData(start, result);
                    }
                };
                // The remote service that should be implemented
                rpcService.getPatients(start, length, callback);
            }
        };

        display.setProvider(provider, patientsData.size());
    }

    private void fetchContactDetails() {
        rpcService.getPatients(new AsyncCallback<List<PatientDTO>>() {
            public void onSuccess(List<PatientDTO> result) {
                System.out.println("on success " + this.getClass().getCanonicalName());
                List<PatientDTO> patientsDTO = result;
                // List<String> data = new ArrayList<String>();
//				display.addData(result);
                // for (int i = 0; i < result.size(); ++i) {
                // data.add(patientsDTO.toString());// .get(i).getAddress());
                // }
                // display.setData(data);
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error fetching contact details");
            }
        });
    }

//	provider = new AsyncDataProvider<PatientDTO>()
//
//	{
//		@Override
//		protected void onRangeChanged (HasData < PatientDTO > display) {
//		final int start = display.getVisibleRange().getStart();
//		int length = display.getVisibleRange().getLength();
//		AsyncCallback<List<PatientDTO>> callback = new AsyncCallback<List<PatientDTO>>() {
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert(caught.getMessage());
//			}
//
//			@Override
//			public void onSuccess(List<PatientDTO> result) {
//				updateRowData(start, result);
//			}
//		};
//		// The remote service that should be implemented
//		remoteService.fetchPage(start, length, callback);
//	}
//	}
}
