package fr.shorimcban.events;

import fr.shorimcban.sanctions.Target;
import fr.shorimcban.sanctions.sanction.TempMute;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BlockChatEvent implements Listener {
	
	@EventHandler
	public void ChatEvent(net.md_5.bungee.api.event.ChatEvent event) {
		
		if(!(event.getSender() instanceof ProxiedPlayer))
			return;
		
		ProxiedPlayer player = (ProxiedPlayer) event.getSender();
		String player_name = player.getName();
		TempMute mute = new TempMute(player_name);
		
		if(mute.isSanctionned(Target.ip)) {
			
			if(!(event.getMessage().indexOf("/", 0) == -1)) {
				
				return;
			}
			
			if(mute.getTime(Target.ip).getTime() > System.currentTimeMillis()) {
				player.sendMessage("§eVous avez été mute par §c"+ mute.getSender(Target.name) + "§e pour§6" + mute.getReason(Target.name) + "§e jusqu'au §c" + mute.getTime(Target.name).toLocalDateTime());
				event.setCancelled(true);
		  } else 
				mute.SanctionExpired();

		}
	}

}
