package fr.shorimcban.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.shorimcban.Main;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class IPMethods {
	
	public void writeIP(ProxiedPlayer player) {
		
		if(hasIP(player.getName())) {
		
			return;
		}
	
		Connection connection = Main.getInstance().getDataBase().getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO IP (player_name, ip) VALUES (?,?)");
			ps.setString(1, player.getName());
			ps.setString(2, player.getAddress().getHostString());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getIp(String player) {
		
		Connection connection = Main.getInstance().getDataBase().getConnection();

		if (!hasIP(player)) {
			
			return null;
		}
		
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT ip FROM IP WHERE player_name = ?");
			ps.setString(1, player);
			ResultSet rs = ps.executeQuery();
			String balance = null;
			
			while(rs.next()) {
				
				balance = rs.getString("ip");
			}
			
			ps.close();
			
			return balance;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
  public boolean hasIP(String player) {
	  
	  Connection connection = Main.getInstance().getDataBase().getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT player_name FROM IP WHERE player_name = ?");
			ps.setString(1, player);
			ResultSet rs = ps.executeQuery();
			boolean hasacount = rs.next();
			ps.close();
			
			return hasacount;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return false;
  }

}
