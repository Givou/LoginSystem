package LoginSystem;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import Login.main;

public class Manager implements Listener{
	public static HashMap<Player, Location> notloggedIn = new HashMap<>();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e ) {
		Player p = e.getPlayer();
		if(notloggedIn.containsKey(p)) {
			p.teleport(notloggedIn.get(p));
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(notloggedIn.containsKey(p)) {
			e.setCancelled(true);
			p.sendMessage("§8» §cDer Chat ist erst nach dem einloggen verfügbar!");
		}
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if(notloggedIn.containsKey(p)) {
			String[] command = e.getMessage().split(" ");
			if(command[0].equals("/login") || command[0].equals("/l") || command[0].equals("/register") || command[0].equals("/logout") || command[0].equals("/changepassword")) {
				return;
			} else {
			e.setCancelled(true);
			p.sendMessage("§8» §cBefehle sind erst nach dem einloggen verfügbar!");
			}
		}
	}
	
	@EventHandler
	public void onInventory(InventoryClickEvent e) {
		try {
			Player p = (Player)e.getWhoClicked();
			if(notloggedIn.containsKey(p)) {
				e.setCancelled(true);
			}
		} catch (Exception e2) {
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if(notloggedIn.containsKey(p)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onFight(EntityDamageByEntityEvent e) {
		try {
			if(e.getEntity() instanceof Player) {
			Player p = (Player)e.getEntity();
			if(notloggedIn.containsKey(p)) {
				e.setCancelled(true);
			}
			}
		} catch (Exception e1) {
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		try {
			Player p = e.getPlayer();
			if(notloggedIn.containsKey(p)) {
				e.setCancelled(true);
			}
		} catch (Exception e1) {
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		try {
			Player p = e.getPlayer();
			if(notloggedIn.containsKey(p)) {
				e.setCancelled(true);
			}
		} catch (Exception e1) {
		}
	}
	
	
	
	
	public static void sendMessage(Player p) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(main.instance, new Runnable() {
			
			@Override
			public void run() {
				if(PasswortManager.hasAccount(p.getUniqueId().toString()) == true) {
					p.sendMessage("§eCookieLogin §8» §7Bitte logge dich mit §8'§c/login Passwort§8' §7in deinen §aAccount §7ein!");
				} else {
					p.sendMessage("§eCookieLogin §8» §7Bitte sichere deinen §aAccount §7mit §8'§c/register Passwort Passwort§8' §7per Passwort ab!");					
				}
			}
		}, 3);
	}
	
	public static void startSystem() {
		
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(notloggedIn.containsKey(all)) {
						if(PasswortManager.hasAccount(all.getUniqueId().toString())) {
						ActionbarPackage Actionpackage = new ActionbarPackage();
						Actionpackage.setActionBar(all, "§8» §7Du musst dich noch mit §8'§c/login Passwort§8' §7einloggen!");
						}
					}
				}
				
			}
		}.runTaskTimer(main.instance, 20, 20);
	}
}
