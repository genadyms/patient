package com.gmail.genadyms.web.view;

import com.gmail.genadyms.shared.dto.PatientDTO;
import com.gmail.genadyms.web.presenter.PatientsPresenter;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.Date;

import static com.gmail.genadyms.web.view.ConstantsValue.FIRST_NAME;

public class PatientsView extends Composite implements PatientsPresenter.Display {
    private final Button addButton;
    private final CellTable<PatientDTO> table;
    public final static int PATIENT_PAGE_SIZE = 5;


    public PatientsView() {

        table = new CellTable<PatientDTO>();
        table.setPageSize(PATIENT_PAGE_SIZE);

        TextColumn<PatientDTO> firstName = new TextColumn<PatientDTO>() {
            @Override
            public String getValue(PatientDTO patient) {
                return patient.getFirstName();
            }

        };
        table.addColumn(firstName, ConstantsValue.FIRST_NAME.toString());


        TextColumn<PatientDTO> lastName = new TextColumn<PatientDTO>() {
            @Override
            public String getValue(PatientDTO patient) {
                return patient.getLastName();
            }

        };
        table.addColumn(lastName, ConstantsValue.LAST_NAME.toString());

        TextColumn<PatientDTO> diagnosis = new TextColumn<PatientDTO>() {
            @Override
            public String getValue(PatientDTO patient) {
                return patient.getDiagnosis();
            }
        };
        table.addColumn(diagnosis, ConstantsValue.DIAGNOSIS.toString());

        TextColumn<PatientDTO> comingDate = getPatientDTOTextColumn(ConstantsValue.COMING_DATE);
        table.addColumn(comingDate, ConstantsValue.COMING_DATE.toString());

        TextColumn<PatientDTO> leavingDate = getPatientDTOTextColumn(ConstantsValue.LEAVING_DATE);
        table.addColumn(leavingDate, ConstantsValue.LEAVING_DATE.toString());

        TextColumn<PatientDTO> ward = new TextColumn<PatientDTO>() {
            @Override
            public String getValue(PatientDTO patient) {
                return String.valueOf(patient.getNumberWard());
            }
        };
        table.addColumn(ward, ConstantsValue.WARD.toString());

        table.setTitle(ConstantsValue.TABLE_TITLE.toString());

        SimplePager pager = new SimplePager(SimplePager.TextLocation.CENTER);
        pager.setDisplay(table);
        VerticalPanel vp = new VerticalPanel();
        initWidget(vp);
        addButton = new Button(ConstantsValue.BUTTON_ADD.toString());
        vp.add(addButton);
        vp.add(table);
        vp.add(pager);
        vp.getHorizontalAlignment();

    }

    private TextColumn<PatientDTO> getPatientDTOTextColumn(final ConstantsValue valueDate) {
        return new TextColumn<PatientDTO>() {
            @Override
            public String getValue(PatientDTO patient) {
                DateTimeFormat dateFormat = DateTimeFormat.getFormat(ConstantsValue.FORMAT_TEMPLATE.toString());
                StringBuffer output = new StringBuffer();
                if (valueDate.equals(ConstantsValue.COMING_DATE)) {
                    if (null != patient.getComingDate()) output.append(dateFormat.format(patient.getComingDate()));
                }
                if (valueDate.equals(ConstantsValue.LEAVING_DATE)) {
                    if (null != patient.getLeavingDate()) output.append(dateFormat.format(patient.getLeavingDate()));
                }
                return output.toString();
            }
        };
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
