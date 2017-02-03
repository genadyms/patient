package com.gmail.genadyms.web.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditPatientEvent extends GwtEvent<EditPatientEventHandler>{
  public static Type<EditPatientEventHandler> TYPE = new Type<EditPatientEventHandler>();
  private final Long id;
  
  public EditPatientEvent(Long id) {
    this.id = id;
  }
  
  public Long getId() { return id; }
  
  @Override
  public Type<EditPatientEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditPatientEventHandler handler) {
    handler.onEditContact(this);
  }
}
