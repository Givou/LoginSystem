package LoginSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConfigManager.MySQL;

public class PasswortManager {
  public static void addPlayer(String name, String uuid, String PW) {
	  saveConnect(uuid);
    MySQL.update("INSERT INTO LoginSystem (Playername, PlayerUUID, Passwort) VALUES ('" + name + "','" + uuid + "','" + PW + "')");
  }
  
  public static void removePlayer(String UUID) {
	  saveConnect(UUID);

    MySQL.update("DELETE FROM LoginSystem WHERE PlayerUUID = '" + UUID + "'");
  }
  
  public static void updatePasswort(String PW, String UUID)
  {
	  saveConnect(UUID);
    MySQL.update("UPDATE LoginSystem SET Passwort='" + PW + "' WHERE PlayerUUID='" + UUID + "'");
  }
  
  public static boolean hasAccount(String UUID) {
	  saveConnect(UUID);
    ResultSet rs = MySQL.getResult("SELECT Playername FROM LoginSystem WHERE PlayerUUID='" + UUID + "'");
    try {
      if (rs.next()) {
        if (rs.getString("Playername") != null) {
          return true;
        }
        return false;
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  
  public static String getPasswort(String uuid) {
	  saveConnect(uuid);
    ResultSet rs = MySQL.getResult("SELECT * FROM LoginSystem WHERE PlayerUUID='" + uuid + "'");
    try {
      if (rs.next()) {
        return rs.getString("Passwort");
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static String saveConnect(String uuid) {
	    ResultSet rs = MySQL.getResult("SELECT * FROM LoginSystem WHERE PlayerUUID='" + uuid + "'");
	    try {
	      if (rs.next()) {
	        return rs.getString("Passwort");
	      }
	    }
	    catch (SQLException e) {
	    }
	    return null;
	  }
}
