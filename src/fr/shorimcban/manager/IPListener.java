package fr.shorimcban.manager;

import fr.shorimcban.Main;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class IPListener implements Listener {

	
	@EventHandler
	public void onPreLogin(PostLoginEvent event) {

		IPMethods IP = Main.getInstance().getIP();
		
		ProxiedPlayer player = event.getPlayer();
		
		if(!IP.hasIP(player.getName())) {
			
			IP.writeIP(player);
		}
		
	}
}
