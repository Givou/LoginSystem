package LoginSystem;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Login.TitleManager;


public class COMMAND_LOGIN implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		
		if(PasswortManager.hasAccount(p.getUniqueId().toString()) == true) {
			if(args.length == 0) {
				p.sendMessage("§eCookieLogin §8» §7Verwende: §e/login Passwort");								
			} else if(args.length == 1) {
				if(Manager.notloggedIn.containsKey(p)) {
				String pw = PasswortManager.getPasswort(p.getUniqueId().toString());
				try {
					String cryptPW = PasswortEncrypter.SHA512(args[0]);
					
					if(cryptPW.equals(pw)) {
						p.sendMessage("§eCookieLogin §8» §7Du hast dich erfolgreich §aeingeloggt§7!");
						Manager.notloggedIn.remove(p);
						JoinEvent.stopKick.add(p);
						
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 18, 20);
						TitleManager title = new TitleManager("&eCookieLogin", "&7Willkommen zurück, &a"+p.getName()+"&7!", 2, 3, 2);
						title.send(p);
						ActionbarPackage Actionpackage = new ActionbarPackage();
						Actionpackage.setActionBar(p, "");

					} else {
						p.sendMessage("§eCookieLogin §8» §cDas eingegebene Passwort ist falsch!");																						
					}
				} catch (NoSuchAlgorithmException e) {
				} catch (UnsupportedEncodingException e) {
				}

				} else {
					p.sendMessage("§eCookieLogin §8» §7Du bist bereits eingeloggt!");																											
				}
			}
		} else {
			p.sendMessage("§eCookieLogin §8» §7Dieser Account ist noch nicht registriert!");																	
		}
		return false;
	}
	
	

}
