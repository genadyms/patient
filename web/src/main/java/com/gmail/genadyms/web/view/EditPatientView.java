package com.gmail.genadyms.web.view;

import com.gmail.genadyms.web.presenter.EditPatientPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import java.util.Date;

public class EditPatientView extends Composite implements EditPatientPresenter.Display {

    private final TextBox firstName;
    private final TextBox lastName;
    private final TextBox address;
    private final TextBox diagnosis;
    private final FlexTable detailsTable;
    private final Button saveButton;
    private final Button cancelButton;
    private final DateBox leavingDateBox;
    private final DateBox comingDateBox;
    private final ListBox wardsListBox;

    public EditPatientView() {
        DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
        contentDetailsDecorator.setWidth("18em");
        initWidget(contentDetailsDecorator);

        VerticalPanel contentDetailsPanel = new VerticalPanel();
        contentDetailsPanel.setWidth("100%");

        detailsTable = new FlexTable();
        detailsTable.setCellSpacing(0);
        detailsTable.setWidth("100%");
        detailsTable.addStyleName("contacts-ListContainer");
        detailsTable.getColumnFormatter().addStyleName(1, "add-contact-input");
        firstName = new TextBox();
        lastName = new TextBox();
        address = new TextBox();
        diagnosis = new TextBox();

        wardsListBox = new ListBox();
        ;

        DateTimeFormat dateFormat = DateTimeFormat.getFormat("MM/dd/yyyy");
        leavingDateBox = new DateBox();
        leavingDateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
        leavingDateBox.getDatePicker().setYearArrowsVisible(true);


        comingDateBox = new DateBox();
        comingDateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
        comingDateBox.getDatePicker().setYearArrowsVisible(true);

        initDetailsTable();
        contentDetailsPanel.add(detailsTable);

        HorizontalPanel menuPanel = new HorizontalPanel();
        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");
        menuPanel.add(saveButton);
        menuPanel.add(cancelButton);
        contentDetailsPanel.add(menuPanel);
        contentDetailsDecorator.add(contentDetailsPanel);
    }

    private void initDetailsTable() {
        detailsTable.setWidget(0, 0, new Label("Firstname"));
        detailsTable.setWidget(0, 1, firstName);
        detailsTable.setWidget(1, 0, new Label("Lastname"));
        detailsTable.setWidget(1, 1, lastName);
        detailsTable.setWidget(2, 0, new Label("Address"));
        detailsTable.setWidget(2, 1, address);
        detailsTable.setWidget(3, 0, new Label("Diagnosis"));
        detailsTable.setWidget(3, 1, diagnosis);
        Label comingDateLabel = new Label();
        comingDateLabel.setText("Time coming:");
        detailsTable.setWidget(4, 0, new Label("Ward:"));
        detailsTable.setWidget(4, 1, wardsListBox);

        detailsTable.setWidget(5, 0, comingDateLabel);
        detailsTable.setWidget(5, 1, comingDateBox);
        Label leavingDateLabel = new Label();
        leavingDateLabel.setText("Time leaving:");
        detailsTable.setWidget(6, 0, leavingDateLabel);
        detailsTable.setWidget(6, 1, leavingDateBox);

        firstName.setFocus(true);

    }


    @Override
    public HasValue<Date> getComingDateBox() {
        return comingDateBox;
    }

    @Override
    public HasValue<Date> getLeavingDateBox() {
        return leavingDateBox;
    }

    @Override
    public ListBox getWardsListBox() {
        return wardsListBox;
    }

    @Override
    public HasValue<String> getFirstName() {
        return firstName;
    }

    @Override
    public HasValue<String> getLastName() {
        return lastName;
    }

    @Override
    public HasValue<String> getAddress() {
        return address;
    }

    @Override
    public HasValue<String> getDiagnosis() {
        return diagnosis;
    }

    @Override
    public HasClickHandlers getSaveButton() {
        return saveButton;
    }

    @Override
    public HasClickHandlers getCancelButton() {
        return cancelButton;
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}
