package com.gmail.genadyms.web.view;

import com.gmail.genadyms.web.presenter.EditPatientPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EditPatientView extends Composite implements EditPatientPresenter.Display {
    private final TextBox firstName;
    private final TextBox lastName;
    private final TextBox address;
    private final FlexTable detailsTable;
    private final Button saveButton;
    private final Button cancelButton;

    public EditPatientView() {
        DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
        contentDetailsDecorator.setWidth("18em");
        initWidget(contentDetailsDecorator);

        VerticalPanel contentDetailsPanel = new VerticalPanel();
        contentDetailsPanel.setWidth("100%");

        // Create the contacts list
        //
        detailsTable = new FlexTable();
        detailsTable.setCellSpacing(0);
        detailsTable.setWidth("100%");
        detailsTable.addStyleName("contacts-ListContainer");
        detailsTable.getColumnFormatter().addStyleName(1, "add-contact-input");
        firstName = new TextBox();
        lastName = new TextBox();
        address = new TextBox();
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
        firstName.setFocus(true);
    }

    public HasValue<String> getFirstName() {
        return firstName;
    }

    public HasValue<String> getLastName() {
        return lastName;
    }

    public HasValue<String> getAddress() {
        return address;
    }

    public HasClickHandlers getSaveButton() {
        return saveButton;
    }

    public HasClickHandlers getCancelButton() {
        return cancelButton;
    }

    public Widget asWidget() {
        return this;
    }
}
