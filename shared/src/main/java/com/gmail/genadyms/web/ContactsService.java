package com.gmail.genadyms.web;

import com.gmail.genadyms.shared.Contact;
import com.gmail.genadyms.shared.ContactDetails;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;

@RemoteServiceRelativePath("contactsService")
public interface ContactsService extends RemoteService {

    Contact addContact(Contact contact);

    Boolean deleteContact(String id);

    ArrayList<ContactDetails> deleteContacts(ArrayList<String> ids);

    ArrayList<ContactDetails> getContactDetails();

    Contact getContact(String id);

    Contact updateContact(Contact contact);
}
