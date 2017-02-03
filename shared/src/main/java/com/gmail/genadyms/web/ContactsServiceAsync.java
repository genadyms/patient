package com.gmail.genadyms.web;

import com.gmail.genadyms.shared.Contact;
import com.gmail.genadyms.shared.ContactDetails;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;

/**
 * Created by Геннадий on 03.02.2017.
 */
public interface ContactsServiceAsync {

    public void addContact(Contact contact, AsyncCallback<Contact> callback);
    public void deleteContact(String id, AsyncCallback<Boolean> callback);
    public void deleteContacts(ArrayList<String> ids, AsyncCallback<ArrayList<ContactDetails>> callback);
    public void getContactDetails(AsyncCallback<ArrayList<ContactDetails>> callback);
    public void getContact(String id, AsyncCallback<Contact> callback);
    public void updateContact(Contact contact, AsyncCallback<Contact> callback);
}