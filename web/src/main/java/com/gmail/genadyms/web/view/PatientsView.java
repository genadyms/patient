package com.gmail.genadyms.web.view;

import com.gmail.genadyms.shared.dto.PatientDTO;
import com.gmail.genadyms.web.presenter.PatientsPresenter;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.Date;

public class PatientsView extends Composite implements PatientsPresenter.Display {
    private final Button addButton;
    private final CellTable<PatientDTO> table;
    private static final int PATIENT_PAGE_SIZE = 5;

    public PatientsView() {

        table = new CellTable<PatientDTO>();
        table.setPageSize(PATIENT_PAGE_SIZE);

        TextColumn<PatientDTO> firstName = new TextColumn<PatientDTO>() {
            @Override
            public String getValue(PatientDTO patient) {
                return patient.getFirstName();
            }

        };
        table.addColumn(firstName, "First name");


        TextColumn<PatientDTO> lastName = new TextColumn<PatientDTO>() {
            @Override
            public String getValue(PatientDTO patient) {
                return patient.getLastName();
            }

        };
        table.addColumn(lastName, "Last name");

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

        SimplePager pager = new SimplePager(SimplePager.TextLocation.CENTER);
        pager.setDisplay(table);

        VerticalPanel vp = new VerticalPanel();
        initWidget(vp);
        addButton = new Button(" Add patient ");
        vp.add(addButton);
        vp.add(table);
        vp.add(pager);
        vp.getHorizontalAlignment();

    }

    @Override
    public HasClickHandlers getAddButton() {
        return addButton;
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public CellTable<PatientDTO> getPatientsTable() {
        return table;
    }

}
