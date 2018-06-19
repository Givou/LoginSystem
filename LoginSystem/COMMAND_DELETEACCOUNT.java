package LoginSystem;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import Login.main;

public class COMMAND_DELETEACCOUNT implements CommandExecutor{
	
	private ArrayList<Player> canDelete = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		
		//Hier noch PremiumCheck einbauen
		if(args.length == 0) {
			p.sendMessage("§eCookieLogin §8» §7Verwende: §e/deleteaccount Passwort");								
		} else if(args.length == 1) {
			if(args[0].equalsIgnoreCase("confirm")) {
				if(canDelete.contains(p)) {
					p.sendMessage("§eCookieLogin §8» §cAccount wird gelöscht..");		
					Bukkit.getScheduler().scheduleSyncDelayedTask(main.instance, new Runnable() {
						
						@Override
						public void run() {
							p.kickPlayer("§8- §eCookieLogin §8-\n§cAlle gespeicherten Accountdaten wurden gelöscht!\n\n§7Du benötigst Hilfe?\n§8> §ats.simplemc.de\n§8> §aforum.simplemc.de");
							
							PasswortManager.removePlayer(p.getUniqueId().toString());
							File playerdata = new File("world/playerdata/"+p.getUniqueId()+".dat");
							playerdata.delete();
						}
					}, 40);
				} else {
					p.sendMessage("§eCookieLogin §8» §7Verwende: §e/deleteaccount Passwort");													
				}
			} else {
				//Wenn kein premium
				String pwEingabe = args[0];
				try {
					String cryptedPW = PasswortEncrypter.SHA512(pwEingabe);
					String dataPW = PasswortManager.getPasswort(p.getUniqueId().toString());
					
					if(dataPW.equals(cryptedPW)) {
						p.sendMessage("§cAchtung: §7Mit dem Löschen deines Account’s verlierst du alle deine Items und Fortschritte, die du auf §aSimpleMC§7 gemacht hast! Bitte bestätige die Löschung mit §c/deleteaccount confirm§7!");
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 18, 20);
						canDelete.add(p);
						
						Bukkit.getScheduler().scheduleSyncDelayedTask(main.instance, new Runnable() {
							
							@Override
							public void run() {
								canDelete.remove(p);
								p.sendMessage("§eCookieLogin §8» §cDie Zeit zum bestätigen der Löschung ist abgelaufen!");																						
							}
						}, 1200);
					} else {
						p.sendMessage("§eCookieLogin §8» §cDas eingegebene Passwort ist falsch!");																						
					}
				} catch (NoSuchAlgorithmException e) {
				} catch (UnsupportedEncodingException e) {
				}
			}
		}
		return false;
	}
	
	

}
