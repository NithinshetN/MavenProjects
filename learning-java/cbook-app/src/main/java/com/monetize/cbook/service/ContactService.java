package com.monetize.cbook.service;

import com.monetize.cbook.domain.Contact;

import java.util.List;
import java.util.UUID;

public interface ContactService {

  Contact addContact(Contact contact);
  List<Contact> getContacts();
  List<Contact> searchContact(String str);
  Contact getContact(UUID id);
  Contact updateContact(Contact contact);
  boolean deleteContact(UUID id);

}
