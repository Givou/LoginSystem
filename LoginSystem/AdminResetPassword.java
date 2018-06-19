package LoginSystem;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminResetPassword implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		
		if(p.isOp()) {
		
	  if(args.length == 0) {
			p.sendMessage("§eCookieLogin §8» §7Verwende: §e/adminresetpassword [Username]");										  
	  } else if(args.length == 1) {
		  Player offline = Bukkit.getPlayer(args[0]);
		  if(offline != null) {
				p.sendMessage("§eCookieLogin §8» §cPasswort wurde von gewünschten Konto entfernt!");
				PasswortManager.removePlayer(offline.getUniqueId().toString());
				offline.kickPlayer("§cDein Passwort wurde entfernt!");
		  } else {
				p.sendMessage("§eCookieLogin §8» §4Fehler: §cSpieler wurde nicht gefunden!");										  		  			  
		  }
	  } else {
			p.sendMessage("§eCookieLogin §8» §7Verwende: §e/adminresetpassword [Username]");										  		  
	  }
	  
		} else {
			p.sendMessage("§eCookieLogin §8» §cDu hast nicht die benötigten Berechtigungen diesen Befehl zu verwenden!");		
		}
		return false;
	}


}
