package com.gmail.genadyms.web.view;

import com.gmail.genadyms.web.presenter.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;

import java.util.List;

public class PatientsView extends Composite implements PatientsPresenter.Display {

	@Override
	public List<String> getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(List<String> value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValue(List<String> value, boolean fireEvents) {
		// TODO Auto-generated method stub

	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<List<String>> handler) {
		// TODO Auto-generated method stub
		return null;
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

}
