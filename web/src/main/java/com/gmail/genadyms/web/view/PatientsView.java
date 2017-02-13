package com.gmail.genadyms.web.view;

import com.gmail.genadyms.shared.dto.PatientDTO;
import com.gmail.genadyms.web.presenter.PatientsPresenter;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientsView extends Composite implements PatientsPresenter.Display {
    private final Button addButton;
    private FlexTable patientsTable;
    private final FlexTable contentTable;
    private final CellTable<PatientDTO> table = new CellTable<PatientDTO>();

    public PatientsView() {
        DecoratorPanel contentTableDecorator = new DecoratorPanel();
        initWidget(contentTableDecorator);
        contentTableDecorator.setWidth("80%");

//		contentTableDecorator.setWidth("18em");

        contentTable = new FlexTable();
//		 contentTable.setWidth("100%");
//		 contentTable.getCellFormatter().addStyleName(0, 0,
//		 "contacts-ListContainer");
//		 contentTable.getCellFormatter().setWidth(0, 0, "100%");
//		 contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0,
//		 DockPanel.ALIGN_TOP);

        // Create the menu
        //
        HorizontalPanel hPanel = new HorizontalPanel();
//		 hPanel.setBorderWidth(0);
//		 hPanel.setSpacing(0);
//		 hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
//        addButton = new Button("Add");
//		 hPanel.add(addButton);
//		 contentTable.getCellFormatter().addStyleName(0, 0,
//		 "contacts-ListMenu");
//		 contentTable.setWidget(0, 0, hPanel);
        //
        // // Create the contacts list
        // //
        patientsTable = new FlexTable();
//		patientsTable.setCellSpacing(0);
//		patientsTable.setCellPadding(0);
//		patientsTable.setWidth("100%");
//		patientsTable.addStyleName("contacts-ListContents");
//		patientsTable.getColumnFormatter().setWidth(0, "15px");
//		contentTable.setWidget(1, 0, patientsTable);

//		contentTableDecorator.add(contentTable);

        table.setPageSize(3);

        TextColumn<PatientDTO> fullName = new TextColumn<PatientDTO>() {

            @Override
            public String getValue(PatientDTO patient) {
                String fullName = patient.getFirstName() + " " + patient.getLastName();
                return fullName;
            }

        };
        table.addColumn(fullName, "Name");

        TextColumn<PatientDTO> diagnosis = new TextColumn<PatientDTO>() {

            @Override
            public String getValue(PatientDTO patient) {
                return patient.getDiagnosis();
            }

        };
        table.addColumn(diagnosis, "Diagnosis");

        DateCell dateComingCell = new DateCell();
        Column<PatientDTO, Date> dateComingColumn = new Column<PatientDTO, Date>(dateComingCell) {

            @Override
            public Date getValue(PatientDTO patient) {
                return patient.getComingDate();
            }

        };
        table.addColumn(dateComingColumn, "Coming");

        DateCell dateLeavingCell = new DateCell();
        Column<PatientDTO, Date> dateLeavingColumn = new Column<PatientDTO, Date>(dateLeavingCell) {

            @Override
            public Date getValue(PatientDTO patient) {
                return patient.getLeavingDate();
            }

        };
        table.addColumn(dateLeavingColumn, "Leaving");

        TextColumn<PatientDTO> ward = new TextColumn<PatientDTO>() {

            @Override
            public String getValue(PatientDTO patient) {
                return String.valueOf(patient.getWard());
            }

        };
        table.addColumn(ward, "Ward");


//		http://www.mytechtip.com/2010/11/gwt-celltable-example-using_8168.html
//		http://stackoverflow.com/questions/17859782/implement-simplepager-with-datagrid-and-asyncdataprovider
//		// Associate an async data provider to the table
//	    AsyncDataProvider<Contact> provider = new AsyncDataProvider<Contact>() {
//	      @Override
//	      protected void onRangeChanged(HasData<Contact> display) {
//	        final int start = display.getVisibleRange().getStart();
//	        int length = display.getVisibleRange().getLength();
//	        AsyncCallback<List<Contact>> callback = new AsyncCallback<List<Contact>>() {
//	          @Override
//	          public void onFailure(Throwable caught) {
//	            Window.alert(caught.getMessage());
//	          }
//	          @Override
//	          public void onSuccess(List<Contact> result) {
//	            updateRowData(start, result);
//	          }
//	        };
//	        // The remote service that should be implemented
//	        remoteService.fetchPage(start, length, callback);
//	      }
//	    };
        table.setTitle("table title");

        SimplePager pager = new SimplePager();
        pager.setDisplay(table);
        VerticalPanel vp = new VerticalPanel();
        addButton = new Button(" Add patient ");
        vp.add(addButton);
        vp.add(table);
        vp.add(pager);
        contentTableDecorator.add(vp);
    }

    public HasClickHandlers getAddButton() {
        return addButton;
    }

//    public HasClickHandlers getList() {
//        return patientsTable;
//    }

    public void setProvider(AsyncDataProvider<PatientDTO> provider) {

        provider.addDataDisplay(table);

    }

//    public void setData(List<String> data) {
//        patientsTable.removeAllRows();
//
//        for (int i = 0; i < data.size(); ++i) {
//            // patientsTable.setWidget(i, 0, new CheckBox());
//            patientsTable.setText(i, 1, data.get(i));
//        }
//    }

//    public int getClickedRow(ClickEvent event) {
//        int selectedRow = -1;
//        HTMLTable.Cell cell = patientsTable.getCellForEvent(event);
//
//        if (cell != null) {
//            // Suppress clicks if the user is actually selecting the
//            // check box
//            //
//            if (cell.getCellIndex() > 0) {
//                selectedRow = cell.getRowIndex();
//            }
//        }
//
//        return selectedRow;
//    }

    public Widget asWidget() {
        return this;
    }

    public CellTable<PatientDTO> getPatientsTable() {
        return table;
    }
}
