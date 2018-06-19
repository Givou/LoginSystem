package LoginSystem;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class COMMAND_CHANGEPASSWORD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		if(Manager.notloggedIn.containsKey(p)) {
			p.sendMessage("§eCookieLogin §8» §7Du bist nicht eingeloggt!");
		} else {
			if(args.length == 0) {
				p.sendMessage("§eCookieLogin §8» §7Verwende: §e/changepassword altesPasswort neuesPasswort");								
			} else if(args.length == 1) {
				p.sendMessage("§eCookieLogin §8» §7Verwende: §e/changepassword altesPasswort neuesPasswort");												
			} else if(args.length == 2) {
				String MySQLaltesPW = PasswortManager.getPasswort(p.getUniqueId().toString());
				String altesPW = args[0];
				String neuesPW = args[1];
				if(neuesPW.length() > 5) {

				try {
					String altesPWCrypt = PasswortEncrypter.SHA512(altesPW);

				
				if(MySQLaltesPW.equals(altesPWCrypt)) {
					try {
						String pw = PasswortEncrypter.SHA512(neuesPW);	
						PasswortManager.updatePasswort(pw, p.getUniqueId().toString());
					} catch (NoSuchAlgorithmException e) {
					} catch (UnsupportedEncodingException e) {
					}
					p.sendMessage("§eCookieLogin §8» §7Dein Passwort wurde erneuert!");	
				} else {
					p.sendMessage("§eCookieLogin §8» §cDas alte Passwort stimmt nicht mit unseren Datenbanken überein!");																											
				}
				} catch (NoSuchAlgorithmException e1) {
				} catch (UnsupportedEncodingException e1) {
				}
				} else {
					p.sendMessage("§eCookieLogin §8» §7Dein Passwort muss mindestens §e6 §7Ziffern enthalten!");				
				}
			} else {
				p.sendMessage("§eCookieLogin §8» §7Verwende: §e/changepassword altesPasswort neuesPasswort");																
			}
			
		}
		return false;
	}
	
	

}
