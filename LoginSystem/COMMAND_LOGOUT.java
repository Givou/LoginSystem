package LoginSystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_LOGOUT implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		
		if(Manager.notloggedIn.containsKey(p)) {
			p.sendMessage("§eCookieLogin §8» §7Du bist nicht eingeloggt!");
		} else {
			p.sendMessage("§eCookieLogin §8» §7Du wurdest erfolgreich §aabgemeldet§7!\n§eCookieLogin §8» §7Da dies eine manuelle Abmeldung ist, wirst du nicht gekickt!");
			Manager.notloggedIn.put(p, p.getLocation());
		}
		return false;
	}

}
