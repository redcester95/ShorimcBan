package fr.shorimcban.sanctions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.shorimcban.Main;
import fr.shorimcdatabase.DataBaseAPI;

public class Sanctions {
	
	public void Sanction(String target, String sender, String reason, String table) {
		
		DataBaseAPI database = Main.getInstance().getDataBase();
		try {
			PreparedStatement ps = database.getConnection().prepareStatement("INSERT INTO " + table + "(player_name, reason, sender) VALUES (?,?,?)");
			ps.setString(1, target);
			ps.setString(2, reason);
			ps.setString(3, sender);
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void UnSanction(String tableIn, String tableOut, String target, String sender, String reason) {
		
		DataBaseAPI database = Main.getInstance().getDataBase();

	}
	
	public String getVar(String table, String target, String var,Target methods) {
		
		Connection connection = Main.getInstance().getDataBase().getConnection();
		
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT " + var + " FROM " + table + " WHERE " + methods.toString() + " = ?");
			if(methods == Target.name)
				ps.setString(1, target);
			else if (methods == Target.ip)
				ps.setString(1, Main.getInstance().getIP().getIp(target));
			
			ResultSet rs = ps.executeQuery();
			String Var = null;
			
			while (rs.next()) {
				Var = rs.getString(var);	
			}
			
			ps.close();
			
			return Var;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean Boolean(String table, String target, String var, Target methods) {
		
		Connection connection = Main.getInstance().getDataBase().getConnection();
		
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT " + var +" FROM " + table + " WHERE " + methods.toString() + " = ?");
			
			if(methods == Target.name)
				ps.setString(1, target);
			else if (methods == Target.ip)
				ps.setString(1, Main.getInstance().getIP().getIp(target));
			
			ResultSet rs = ps.executeQuery();
			boolean b = rs.next();
			ps.close();
			return b;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	

	public static enum Target{
		
		ip("ip"), 
		name("target");
		
		private String methods = "";

		private Target(String methods) {
			this.methods  = methods;
		}
		
		
		public String toString() {
			return methods;
		}	
	}
	
}
