package com.gmail.genadyms.web.event;

import com.google.gwt.event.shared.EventHandler;

public interface PatientUpdatedEventHandler extends EventHandler{
  void onContactUpdated(PatientUpdatedEvent event);
}
