package com.gmail.genadyms.web.presenter;


import com.gmail.genadyms.web.GreetingServiceAsync;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

/**
 * Created by Геннадий on 02.02.2017.
 */
public class PatientsPresenter implements Presenter {

    private final GreetingServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    public PatientsPresenter(GreetingServiceAsync rpcService, HandlerManager eventBus, Display view) {
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

    }

    public interface Display {//} extends HasValue<List<String>> {

        HasClickHandlers getAddButton();

        HasClickHandlers getList();

        void setData(List<String> data);

        int getClickedRow(ClickEvent event);

        Widget asWidget();
    }
}
