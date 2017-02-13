package com.gmail.genadyms.web.presenter;

import com.gmail.genadyms.shared.dto.PatientDTO;
import com.gmail.genadyms.web.event.AddPatientEvent;
import com.gmail.genadyms.web.event.EditPatientEvent;
import com.gmail.genadyms.web.event.PatientUpdatedEvent;
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
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientsPresenter implements Presenter {

    public interface Display {

        HasClickHandlers getAddButton();

        Widget asWidget();

        CellTable<PatientDTO> getPatientsTable();

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

        final SingleSelectionModel<PatientDTO> selectionModel = new SingleSelectionModel<PatientDTO>();
        display.getPatientsTable().setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                PatientDTO selected = selectionModel.getSelectedObject();
                if (selected != null) {
                    eventBus.fireEvent(new EditPatientEvent(selected.getId()));
                }
            }
        });

        AsyncDataProvider<PatientDTO> provider = new AsyncDataProvider<PatientDTO>() {
            protected void onRangeChanged(HasData<PatientDTO> display) {
                final int start = display.getVisibleRange().getStart();
                int length = display.getVisibleRange().getLength();
                if (display.getRowCount() == 0) updateCount();
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

                rpcService.getPatients(start, length, callback);
            }

            private void updateCount() {
                rpcService.getCountPatients(new AsyncCallback<Long>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("Error fetching count patients");
                    }

                    @Override
                    public void onSuccess(Long result) {
                        updateRowCount(result.intValue(), true);
                    }
                });
            }
        };
        provider.addDataDisplay(display.getPatientsTable());
    }

    public void go(final HasWidgets container) {
        bind();
        container.clear();
        container.add(display.asWidget());
    }

}
