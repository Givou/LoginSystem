package Login;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import ConfigManager.MySQL;
import ConfigManager.MySQLConfig;
import LoginSystem.AdminResetPassword;
import LoginSystem.COMMAND_CHANGEPASSWORD;
import LoginSystem.COMMAND_DELETEACCOUNT;
import LoginSystem.COMMAND_LOGIN;
import LoginSystem.COMMAND_LOGOUT;
import LoginSystem.COMMAND_REGISTER;
import LoginSystem.JoinEvent;
import LoginSystem.Manager;



public class main extends JavaPlugin{
	
	public static main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		Bukkit.getConsoleSender().sendMessage("Ich bin aktiviert! YAYYY");
		MySQLConfig.cConfig();
		MySQLConfig.getIndex();
		MySQL.Connect();
		MySQL.createTable();
		
		Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
		Bukkit.getPluginManager().registerEvents(new Manager(), this);
		
		getCommand("register").setExecutor(new COMMAND_REGISTER());
		getCommand("login").setExecutor(new COMMAND_LOGIN());
		getCommand("logout").setExecutor(new COMMAND_LOGOUT());
		getCommand("changepassword").setExecutor(new COMMAND_CHANGEPASSWORD());
		getCommand("deleteaccount").setExecutor(new COMMAND_DELETEACCOUNT());
		getCommand("adminresetpassword").setExecutor(new AdminResetPassword());
		
		LoginSystem.Manager.startSystem();
		
		
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("Ich bin deaktiviert! OHHHH");

		for(Player all : Bukkit.getOnlinePlayers()) {
			all.kickPlayer("§8- §eCookieLogin §8-\n§cDu wurdest aufgrund eines Reloads gekickt!");
		}

	}

}
