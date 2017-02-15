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
    private static final int PATIENT_PAGE_SIZE = 5;

    public PatientsView() {
        DecoratorPanel contentTableDecorator = new DecoratorPanel();
        initWidget(contentTableDecorator);
        contentTableDecorator.setWidth("80%");

        contentTable = new FlexTable();
        patientsTable = new FlexTable();
        table.setPageSize(PATIENT_PAGE_SIZE);

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
                return String.valueOf(patient.getNumberWard());
            }

        };
        table.addColumn(ward, "Ward");
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

    public Widget asWidget() {
        return this;
    }

    public CellTable<PatientDTO> getPatientsTable() {
        return table;
    }
}
