package fr.shorimcban;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main instance;
	
	@Override
	public void onEnable() {
	
		instance = this;
		System.out.println("[ShorimcBan] Initialisation du plugin");
	}
	
	@Override
	public void onDisable() {
		
		System.out.println("[ShorimcBan] Desactivation du plugin");
	}
	
	public static Main getInstance() {
		return instance;
	}

}
