package com.gmail.genadyms.web.event;

import com.google.gwt.event.shared.EventHandler;

public interface PatientDeletedEventHandler extends EventHandler {
  void onContactDeleted(PatientDeletedEvent event);
}
