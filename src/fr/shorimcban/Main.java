package fr.shorimcban;

import fr.shorimcban.events.BlockChatEvent;
import fr.shorimcban.events.BlockConnectionEvent;
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
		getDataBase().connect("localhost", "Sanctions", 3306, "MinecraftUser", "ShoriMCDataBase");
		getDataBase().CreateTable("IP", "IP(id INT PRIMARY KEY AUTO_INCREMENT, player_name VARCHAR(255),ip VARCHAR(255))");
		getDataBase().CreateTable("KickManager", "KickManager(id INT PRIMARY KEY AUTO_INCREMENT, player_name VARCHAR(255), sender VARCHAR(255), reason VARCHAR(255), date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, ip VARCHAR(255))");
		getDataBase().CreateTable("BanManager", "BanManager(id INT PRIMARY KEY AUTO_INCREMENT, player_name VARCHAR(255), sender VARCHAR(255), reason VARCHAR(255), date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, ip VARCHAR(255))");
		getDataBase().CreateTable("UnBanManager", "UnBanManager(id INT PRIMARY KEY AUTO_INCREMENT, player_name VARCHAR(255), sender VARCHAR(255), unsender VARCHAR(255) , reason VARCHAR(255), unreason VARCHAR(255), date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, undate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, ip VARCHAR(255))");
		getDataBase().CreateTable("TempBanManager", "TempBanManager(id INT PRIMARY KEY AUTO_INCREMENT, player_name VARCHAR(255), sender VARCHAR(255), reason VARCHAR(255), date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, ip VARCHAR(255))");
		getDataBase().CreateTable("UnTempBanManager", "UnTempBanManager(id INT PRIMARY KEY AUTO_INCREMENT, player_name VARCHAR(255), sender VARCHAR(255), unsender VARCHAR(255) , reason VARCHAR(255), unreason VARCHAR(255), date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, undate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, ip VARCHAR(255))");
		getDataBase().CreateTable("TempMuteManager", "TempMuteManager(id INT PRIMARY KEY AUTO_INCREMENT, player_name VARCHAR(255), sender VARCHAR(255), reason VARCHAR(255), date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, ip VARCHAR(255))");
		getDataBase().CreateTable("UnTempMuteManager", "UnTempMuteManager(id INT PRIMARY KEY AUTO_INCREMENT, player_name VARCHAR(255), sender VARCHAR(255), unsender VARCHAR(255) , reason VARCHAR(255), unreason VARCHAR(255), date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, undate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, ip VARCHAR(255))");
		getProxy().getPluginManager().registerListener(this, new IPListener());
		getProxy().getPluginManager().registerListener(this, new BlockConnectionEvent());
		getProxy().getPluginManager().registerListener(this, new BlockChatEvent());
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
