package fr.shorimcban;

import fr.shorimcban.manager.IPListener;
import fr.shorimcban.manager.IPMethods;
import fr.shorimcdatabase.DataBaseAPI;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
	
	private static Main instance;
	private final DataBaseAPI database = new DataBaseAPI();
	private IPMethods IP = new IPMethods();
	
	@Override
	public void onEnable() {
	
		instance = this;
		getDataBase().connect("localhost", "Sanction", 3306, "MinecraftUser", "ShoriMCDataBase");
		getDataBase().CreateTable("IP", "IP(id INT PRIMARY KEY AUTO_INCREMENT, player_name VARCHAR(255),ip VARCHAR(255))");
		getProxy().getPluginManager().registerListener(this, new IPListener());
		System.out.println("[ShorimcBan] Initialisation du plugin");
		
	}
	
	@Override
	public void onDisable() {
		
		getDataBase().disconnect();
		System.out.println("[ShorimcBan] Desactivation du plugin");
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public DataBaseAPI getDataBase() {
		return database;
	}
	
	public IPMethods getIP() {
		return IP;
	}
}
