package com.monetize.cbook.dao;

import com.monetize.cbook.domain.Contact;
import com.monetize.cbook.util.DbConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.lang.Boolean.TRUE;

public class ContactDaoImplTest {

  private ContactDao contactDao;

  @BeforeEach
  void init() {
    contactDao = new ContactDaoImpl();
    deleteAll();
  }

  @Test
  void getContactsTest() {
    List<Contact> contacts = getContacts();
    for (Contact contact : contacts) {
      contactDao.insertContact(contact);
    }
    List<Contact> contactList = contactDao.selectContacts();
    Assertions.assertEquals(3, contactList.size());
  }

  @Test
  void addContact() {
    Contact contact = getContact();
    contactDao.insertContact(contact);
    List<Contact> list = contactDao.selectContacts();
    Assertions.assertEquals(1, list.size());
  }

  @Test
  void searchTestContact(){
    String str="Jayesh";
    int i=0;
    List<Contact> contacts = getContacts();
    for (Contact contact : contacts) {
      contactDao.insertContact(contact);
    }
    for(Contact c:contacts){
      if(Objects.equals(c.getFirstName(), str)){
        i++;
      }
    }
    List<Contact> contactList = contactDao.searchContact(str);
    Assertions.assertEquals(i, contactList.size());

  }

  @Test
  void selectTestContact(){
    List<Contact> contacts = getContacts();
    for (Contact contact : contacts) {
      contactDao.insertContact(contact);
    }
    Contact contact=contactDao.selectContact(contacts.get(0).getId());
    Assertions.assertEquals(contacts.get(0).getId(),contact.getId());
  }

  @Test
  void updateContacts(){
    List<Contact> contacts = getContacts();
    for (Contact contact : contacts) {
      contactDao.insertContact(contact);
    }
    contacts.get(0).setLastName("Shetty");
    Contact upContact=contactDao.updateContact(contacts.get(0));
    Assertions.assertEquals(contacts.get(0).getLastName(),upContact.getLastName());
  }

  @Test
  void isDeletedTest(){
    List<Contact> contacts = getContacts();
    for (Contact contact : contacts) {
      contactDao.insertContact(contact);
    }
    boolean val= contactDao.deleteContact(contacts.get(1).getId());
//    boolean val= contactDao.deleteContact(UUID.fromString("b38a1d46-2a58-4dec-9df3-a44101c8"));
    Assertions.assertEquals(TRUE,val);
  }
  @Test
  void isExistTest(){
    List<Contact> contacts = getContacts();
    for (Contact contact : contacts) {
      contactDao.insertContact(contact);
    }
    boolean val= contactDao.isContactExists(contacts.get(1).getMobile());
//    boolean val= contactDao.isContactExists("11234567891");
    Assertions.assertEquals(TRUE,val);
  }
  private void deleteAll() {
    Connection con = null;
    Statement st = null;
    try {
      con = DbConnectionUtil.getConnection();
      st = con.createStatement();
      st.executeUpdate("delete from contact");
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DbConnectionUtil.close(st, con);
    }
  }

  Contact getContact() {
    return Contact.builder()
        .id(UUID.randomUUID())
        .firstName("Aruna")
        .lastName("V")
        .email("arun.v@gmail.com")
        .mobile("9876543219")
        .build();
  }

  List<Contact> getContacts() {
    Contact contact1 = Contact.builder()
        .id(UUID.randomUUID())
        .firstName("Aruna")
        .lastName("V")
        .email("arun.v@gmail.com")
        .mobile("9876543219")
        .build();
    Contact contact2 = Contact.builder()
        .id(UUID.randomUUID())
        .firstName("Ramana")
        .lastName("A")
        .email("ramana.a@gmail.com")
        .mobile("9876543217")
        .build();

    Contact contact3 = Contact.builder()
        .id(UUID.randomUUID())
        .firstName("Jayesh")
        .lastName("G")
        .email("jayesh.g@gmail.com")
        .mobile("9876543218")
        .build();
    return List.of(contact3, contact1, contact2);
  }


}
