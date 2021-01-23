package fr.shorimcban.events;

import fr.shorimcban.sanctions.Target;
import fr.shorimcban.sanctions.sanction.Ban;
import fr.shorimcban.sanctions.sanction.TempBan;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BlockConnectionEvent implements Listener {
	
	@EventHandler
	public void onLogin(PostLoginEvent event) {
		
		String player = event.getPlayer().getName();
		Ban ban = new Ban(player);
		
		if(ban.isSanctionned(Target.ip)) {
			event.getPlayer().disconnect("§eVous avez été banni par §c" + ban.getSender(Target.ip) + " §epour§6" + ban.getReason(Target.name));
		}
		
		TempBan tempban = new TempBan(player);
		
		if(tempban.isSanctionned(Target.ip)) {
			
			if(tempban.getTime(Target.ip).getTime() > System.currentTimeMillis())
				event.getPlayer().disconnect("§eVous avez été banni par §c" + tempban.getSender(Target.ip) + "§e pour§6" + tempban.getReason(Target.ip) + "§e jusqu'au §c" + tempban.getTime(Target.ip).toLocalDateTime());
			else 
				tempban.SanctionExpired();

		}		
	}
}
