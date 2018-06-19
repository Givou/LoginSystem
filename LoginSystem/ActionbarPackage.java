package LoginSystem;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class ActionbarPackage {
	
    public void setActionBar(Player player, String message) {
        CraftPlayer p = (CraftPlayer) player;
        IChatBaseComponent ibc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat packet = new PacketPlayOutChat(ibc, (byte) 2);
        p.getHandle().playerConnection.sendPacket(packet);
    }
}
