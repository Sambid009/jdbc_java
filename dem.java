package demo;

import java.sql.*;

public class dem {
  public static void main(String[] args) {
    // establish a connection to the database
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");

      String URL = "jdbc:mysql://localhost:3306/jdbc_db";
      String username = "root";
      String password = "";
      Connection con = DriverManager.getConnection(URL, username, password);

      if (con.isClosed()) {
        System.out.println("Connection is closed");
      } else {
        System.out.println("Connection is open");
      }
      
      // Don't forget to close the connection
      con.close();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
