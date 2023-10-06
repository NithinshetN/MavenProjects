package com.monetize.cbook.dao;

import com.monetize.cbook.domain.Contact;
import com.monetize.cbook.util.DbConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContactDaoImpl implements ContactDao {
  private static final String GET_CONTACTS = "select id,first_name,last_name,mobile,email,deleted from contact where deleted=false";
  private static final String ADD_CONTACT = "insert into contact(id,first_name,last_name,email,mobile) values(?,?,?,?,?)";
  private static final String SEARCH_CONTACT="select id,first_name,last_name,mobile,email,deleted from contact where deleted=false and first_name=?";
  private static final String SEARCH_CONTACT_UUID="select id,first_name,last_name,mobile,email,deleted from contact where deleted=false and id=?";
  private static final String UPDATE_CONTACT="update contact set first_name=?,last_name=?,email=?,mobile=? where id=? and deleted=false";
  private static final String DELETE_CONTACT="update contact set deleted=TRUE where id=? and deleted=FALSE";
  private static final String CONTACT_EXIST="select * from contact where mobile=? and deleted=false";

  public Contact insertContact(Contact contact) {
    Connection con = null;
    PreparedStatement pst = null;
    try {
      con = DbConnectionUtil.getConnection();
      pst = con.prepareStatement(ADD_CONTACT);
      pst.setObject(1,contact.getId());
      pst.setString(2,contact.getFirstName());
      pst.setString(3,contact.getLastName());
      pst.setString(4,contact.getEmail());
      pst.setString(5,contact.getMobile());
      int res= pst.executeUpdate();
      if(res!=0){
        System.out.println("Contact is added with id :"+contact.getId());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      DbConnectionUtil.close(pst,con);
    }
    return contact;
  }

  public List<Contact> selectContacts() {
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    List<Contact> contacts = new ArrayList<>();
    try {
      con = DbConnectionUtil.getConnection();
      st = con.createStatement();
      rs = st.executeQuery(GET_CONTACTS);
      while (rs.next()) {
        Contact contact = covertResSetToContact(rs);
        contacts.add(contact);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DbConnectionUtil.close(rs, st, con);
    }
    return contacts;
  }

  public List<Contact> searchContact(String str) {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    List<Contact> contacts = new ArrayList<>();
    try{
      con=DbConnectionUtil.getConnection();
      pst=con.prepareStatement(SEARCH_CONTACT);
      pst.setString(1,str);
      rs= pst.executeQuery();
      while (rs.next()) {
        Contact contact = covertResSetToContact(rs);
        contacts.add(contact);
      }
    }catch (Exception e){
      e.printStackTrace();
    }finally {
      DbConnectionUtil.close(rs, pst, con);
    }
    return contacts;
  }

  public Contact selectContact(UUID id) {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Contact contact=null;
    try{
      con=DbConnectionUtil.getConnection();
      pst=con.prepareStatement(SEARCH_CONTACT_UUID);
      pst.setObject(1,id);
      rs= pst.executeQuery();
      while (rs.next()) {
        contact = covertResSetToContact(rs);
      }
    }catch (Exception e){
      e.printStackTrace();
    }finally {
      DbConnectionUtil.close(rs, pst, con);
    }
    return contact;
  }

  public Contact updateContact(Contact contact) {
    Connection con = null;
    PreparedStatement pst = null;
    PreparedStatement st=null;
    Contact cont=null;
    ResultSet res=null;
    try {
      con = DbConnectionUtil.getConnection();
      st=con.prepareStatement(SEARCH_CONTACT_UUID);
      pst = con.prepareStatement(UPDATE_CONTACT);
      pst.setString(1,contact.getFirstName());
      pst.setString(2,contact.getLastName());
      pst.setString(3,contact.getEmail());
      pst.setString(4,contact.getMobile());
      pst.setObject(5,contact.getId());
      st.setObject(1,contact.getId());
      int rs=pst.executeUpdate();
      res= st.executeQuery();
      if(rs!=0){
        while (res.next()) {
          cont = covertResSetToContact(res);
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      DbConnectionUtil.close(res,pst, con);
    }
    return cont;
  }

  public boolean deleteContact(UUID id) {
    Connection con = null;
    PreparedStatement pst = null;
    int res=0;
    try{
      con = DbConnectionUtil.getConnection();
      pst = con.prepareStatement(DELETE_CONTACT);
      pst.setObject(1,id);
      res=pst.executeUpdate();
    }catch (Exception e){
      e.printStackTrace();
    }finally {
      DbConnectionUtil.close(pst,con);
    }
      return res != 0;
  }

  public boolean isContactExists(String mobile) {
    Connection con=null;
    PreparedStatement pst=null;
    ResultSet res=null;
    try{
      con = DbConnectionUtil.getConnection();
      pst=con.prepareStatement(CONTACT_EXIST);
      pst.setString(1,mobile);
      res=pst.executeQuery();
      if(res.next()){
        return true;
      }else{
        return false;
      }
    }catch (Exception e){
      e.printStackTrace();
      return false;
    }finally {
      DbConnectionUtil.close(res,pst,con);
    }
  }


  private static Contact covertResSetToContact(ResultSet rs) throws SQLException {
    UUID id = UUID.fromString(rs.getString("id"));
    String firstName = rs.getString("first_name");
    String lastName = rs.getString("last_name");
    String mobile = rs.getString("mobile");
    String email = rs.getString("email");
    boolean deleted = rs.getBoolean("deleted");
    return Contact.builder()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .email(email)
        .mobile(mobile)
        .deleted(deleted)
        .build();
  }
}
