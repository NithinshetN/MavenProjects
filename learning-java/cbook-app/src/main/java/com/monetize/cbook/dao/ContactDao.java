package com.monetize.cbook.dao;

import com.monetize.cbook.domain.Contact;

import java.util.List;
import java.util.UUID;

public interface ContactDao {

      Contact insertContact(Contact contact);
      List<Contact> selectContacts();
      List<Contact> searchContact(String str);
      Contact selectContact(UUID id);
      Contact updateContact(Contact contact);
      boolean deleteContact(UUID id);
      boolean isContactExists(String mobile);


}
