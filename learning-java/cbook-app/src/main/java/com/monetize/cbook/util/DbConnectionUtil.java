package com.monetize.cbook.util;

import java.sql.*;
import java.util.Properties;

public class DbConnectionUtil {

  private static Properties properties;

  static {
    try {
      properties = new Properties();
      properties.load(DbConnectionUtil.class.getResourceAsStream("/db.properties"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Connection getConnection() {
    String url = properties.getProperty("db.url");
    String username = properties.getProperty("db.username");
    String password = properties.getProperty("db.password");
    Connection con = null;
    try {
      con = DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return con;
  }

  public static void close(Statement st, Connection con) {
    try {
      if (st != null)
        st.close();
      if (con != null)
        con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }



  public static void close(ResultSet rs, Statement st, Connection con) {
    try {
      if (st != null)
        st.close();
      if (con != null)
        con.close();
      if (rs != null)
        rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
