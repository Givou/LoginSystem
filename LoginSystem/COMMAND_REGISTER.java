package LoginSystem;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Login.TitleManager;

public class COMMAND_REGISTER implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		if(PasswortManager.hasAccount(p.getUniqueId().toString()) == true) {
			p.sendMessage("§eCookieLogin §8» §7Dieser Account ist bereits registriert. ");
		} else {
			if(args.length == 0) {
				p.sendMessage("§eCookieLogin §8» §7Verwende: §e/register Passwort Passwort");
			} else if(args.length == 1) {
				p.sendMessage("§eCookieLogin §8» §7Verwende: §e/register Passwort Passwort");				
			} else if(args.length == 2) {
				String pw1 = args[0];
				String pw2 = args[1];
				if(pw1.length() > 5) {
				if(pw1.equals(pw2)) {
					p.sendMessage("§eCookieLogin §8» §7Du hast dich erfolgreich §aregistriert§7!");
					try {
						String pw = PasswortEncrypter.SHA512(pw1);			
						PasswortManager.addPlayer(p.getName(), p.getUniqueId().toString(), pw);
					} catch (NoSuchAlgorithmException e) {
					} catch (UnsupportedEncodingException e) {
					}
					
					
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 18, 20);
					TitleManager title = new TitleManager("&eCookieLogin", "&7Willkommen auf CookieBuild, &a"+p.getName()+"&7!", 2, 3, 2);
					title.send(p);
					Manager.notloggedIn.remove(p);
				
				} else {
					p.sendMessage("§eCookieLogin §8» §cDie Passwörter stimmen nicht überein!");									
				}
				
				} else {
					p.sendMessage("§eCookieLogin §8» §7Dein Passwort muss mindestens §e6 §7Ziffern enthalten!");				
				}
			}
		}
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
