package ConfigManager;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

public class MySQLConfig {
	
	public static void cConfig() {
		File folder = new File("plugins/CookieLogin");
		File file = new File("plugins/CookieLogin", "MySQL.yml");
		folder.mkdirs();
		try {
			if(!file.exists()) {
				file.createNewFile();
				
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				cfg.options().copyDefaults(true);
				
				cfg.addDefault("Host:", "DeinHost");
				cfg.addDefault("Port:", "3306");
				cfg.addDefault("Database:", "DeineDatenbank");
				cfg.addDefault("User:", "DeinUsername");
				cfg.addDefault("Password:", "DeinPasswort");
				
				cfg.save(file);


			}
		} catch (Exception e) {
		}
	}
	
	public static void getIndex() {
		File file = new File("plugins/CookieLogin", "MySQL.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		MySQL.host = cfg.getString("Host:");
		MySQL.port = cfg.getString("Port:");
		MySQL.database = cfg.getString("Database:");
		MySQL.user = cfg.getString("User:");
		MySQL.password = cfg.getString("Password:");

	}


}
