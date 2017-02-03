package com.gmail.genadyms.web.event;

import com.gmail.genadyms.shared.PatientDTO;
import com.google.gwt.event.shared.GwtEvent;

public class PatientUpdatedEvent extends GwtEvent<PatientUpdatedEventHandler>{
  public static Type<PatientUpdatedEventHandler> TYPE = new Type<PatientUpdatedEventHandler>();
  private final PatientDTO updatedContact;
  
  public PatientUpdatedEvent(PatientDTO updatedContact) {
    this.updatedContact = updatedContact;
  }
  
  public PatientDTO getUpdatedContact() { return updatedContact; }
  

  @Override
  public Type<PatientUpdatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(PatientUpdatedEventHandler handler) {
    handler.onContactUpdated(this);
  }
}
