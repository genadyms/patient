package com.gmail.genadyms.web.presenter;

import java.util.List;

import com.gmail.genadyms.shared.service.PatientServiceAsync;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class PatientsPresenter implements Presenter {

	private final PatientServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public PatientsPresenter(PatientServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		System.out.println("PRESENTER RUN!!!");
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	private void bind() {
		// TODO Auto-generated method stub

	}

	public interface Display {// extends HasValue<List<String>> {

		HasClickHandlers getAddButton();

		HasClickHandlers getList();

		void setData(List<String> data);

		int getClickedRow(ClickEvent event);

		Widget asWidget();
	}
}
