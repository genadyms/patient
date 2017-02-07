package com.gmail.genadyms.web.view;

import com.gmail.genadyms.web.presenter.PatientsPresenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.i18n.client.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class PatientsView extends Composite implements PatientsPresenter.Display {
	private final Button addButton;
	private FlexTable patientsTable;
	private final FlexTable contentTable;

	public PatientsView() {
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
		contentTableDecorator.setWidth("100%");
		contentTableDecorator.setWidth("18em");

		contentTable = new FlexTable();
		contentTable.setWidth("100%");
		contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListContainer");
		contentTable.getCellFormatter().setWidth(0, 0, "100%");
		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0, DockPanel.ALIGN_TOP);

		// Create the menu
		//
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setBorderWidth(0);
		hPanel.setSpacing(0);
		hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		addButton = new Button("Add");
		hPanel.add(addButton);
		contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListMenu");
		contentTable.setWidget(0, 0, hPanel);

		// Create the contacts list
		//
		patientsTable = new FlexTable();
		patientsTable.setCellSpacing(0);
		patientsTable.setCellPadding(0);
		patientsTable.setWidth("100%");
		patientsTable.addStyleName("contacts-ListContents");
		patientsTable.getColumnFormatter().setWidth(0, "15px");
		contentTable.setWidget(1, 0, patientsTable);




		/*
		 // Create a date picker
    DatePicker datePicker = new DatePicker();
    final Label text = new Label();

    // Set the value in the text box when the user selects a date
    datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
      public void onValueChange(ValueChangeEvent<Date> event) {
        Date date = event.getValue();
        String dateString = DateTimeFormat.getMediumDateFormat().format(date);
        text.setText(dateString);
      }
    });

    // Set the default value
    datePicker.setValue(new Date(), true);

    // Add the widgets to the page
    RootPanel.get().add(text);
    RootPanel.get().add(datePicker);
		*/
		contentTableDecorator.add(contentTable);
	}

	public HasClickHandlers getAddButton() {
		return addButton;
	}

	public HasClickHandlers getList() {
		return patientsTable;
	}



    public void setData(List<String> data) {
		patientsTable.removeAllRows();

		for (int i = 0; i < data.size(); ++i) {
//			patientsTable.setWidget(i, 0, new CheckBox());
			patientsTable.setText(i, 1, data.get(i));
		}
	}

	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = patientsTable.getCellForEvent(event);

		if (cell != null) {
			// Suppress clicks if the user is actually selecting the
			// check box
			//
			if (cell.getCellIndex() > 0) {
				selectedRow = cell.getRowIndex();
			}
		}

		return selectedRow;
	}

	public Widget asWidget() {
		return this;
	}
}
