package ConfigManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {
  public static String host;
  public static String port;
  public static String database;
  public static String user;
  public static String password;
  public static Connection con;
  
  public static void Connect() {
    if (!isConnected()) {
      try {
        con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database+"?autoReconnect=true", user, password);
        System.out.println("MySQL-Verbindung hergestellt!");
      } catch (SQLException e) {
        System.err.println(e.getMessage());
      }
    }
  }
  
  public static void close() {
    if (isConnected()) {
      try {
        con.close();
        System.out.println("MySQL-Verbindung getrennt!");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
  
  public static boolean isConnected() {
    return con != null;
  }
  
  public static void createTable() {
    try {
      con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS LoginSystem (Playername VARCHAR(128), PlayerUUID VARCHAR(128) PRIMARY KEY, Passwort VARCHAR(128))");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public static void update(String qry) {
    if (isConnected()) {
      try {
        con.createStatement().executeUpdate(qry);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
  
  public static ResultSet getResult(String qry) {
    if (isConnected()) {
      try {
        return con.createStatement().executeQuery(qry);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}