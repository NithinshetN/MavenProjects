package com.monetize;

import com.monetize.cbook.dao.ContactDaoImpl;
import com.monetize.cbook.domain.Contact;

import java.util.UUID;

public class Main {
  public static void main(String[] args) {
    ContactDaoImpl cnd=new ContactDaoImpl();
    boolean val=cnd.deleteContact(UUID.fromString("b38a1d46-2a58-4dec-9df3-a44101c8"));
    System.out.println(val);
  }
}