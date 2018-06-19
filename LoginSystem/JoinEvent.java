package LoginSystem;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import Login.main;

public class JoinEvent implements Listener{
	
	public static ArrayList<Player> stopKick = new ArrayList<>();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(!Manager.notloggedIn.containsKey(p)) {
			Manager.notloggedIn.put(p, p.getLocation());
			Manager.sendMessage(p);
		}
		
		new BukkitRunnable() {
			int i = 0;
			@Override
			public void run() {
				i++;
				if(p.isOnline() == false) {
					cancel();
				}
				
				if(stopKick.contains(p)) {
					cancel();
					stopKick.remove(p);
				}
				
				if(i == 30) {
					cancel();
					if(p.isOnline() == true) {
						if(Manager.notloggedIn.containsKey(p)) {
						p.kickPlayer("§8- §eCookieLogin §8-\n§cDeine verfügbare Zeit zum einloggen ist abgelaufen!\n\n§7Du benötigst Hilfe beim einloggen?\n§8> §araizcookie@gmail.com");
						}
						}
				}
				
			}
		}.runTaskTimer(main.instance, 20, 20);
	}
	
	@EventHandler
	public void onLeft(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(Manager.notloggedIn.containsKey(p)) {
			Manager.notloggedIn.remove(p);
		}
	}
	
	
}
