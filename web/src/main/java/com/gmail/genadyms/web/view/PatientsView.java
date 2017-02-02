package com.gmail.genadyms.web.view;

import com.gmail.genadyms.web.presenter.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

public class PatientsView extends Composite implements PatientsPresenter.Display {

	private final Button addButton;
	private FlexTable patientsTable;
	private final FlexTable contentTable;

	public PatientsView() {
		System.out.println("VIEWS RUN!!!!");
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

		contentTableDecorator.add(contentTable);
	}

	@Override
	public HasClickHandlers getAddButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasClickHandlers getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setData(List<String> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getClickedRow(ClickEvent event) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

}
