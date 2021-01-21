package fr.shorimcban.sanctions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import fr.shorimcban.Main;

public abstract class Sanctions {
	
	protected abstract void Sanction(String target, String sender, String reason, String table, Timestamp time);
	
	protected abstract void UnSanction(String tableIn, String tableOut, String target, String sender, String reason);
	
	protected String getStr(String table, String target, String var,Target methods) {
		
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
	
	protected Timestamp getTs(String table, String target, String var,Target methods) {
		
		Connection connection = Main.getInstance().getDataBase().getConnection();
		
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT " + var + " FROM " + table + " WHERE " + methods.toString() + " = ?");
			if(methods == Target.name)
				ps.setString(1, target);
			else if (methods == Target.ip)
				ps.setString(1, Main.getInstance().getIP().getIp(target));
			
			ResultSet rs = ps.executeQuery();
			Timestamp Var = null;
			
			while (rs.next()) {
				Var = rs.getTimestamp(var);
			}
			
			ps.close();
			
			return Var;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	protected boolean Boolean(String table, String target, String var, Target methods) {
		
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
	
	public String getSender(String target, String table,Target methods) {
		
		return this.getStr(table, target, "sender", methods);
	}
	
	public String getReason(String target, String table,Target methods) {
		
		return this.getStr(table, target, "reason", methods);
	}
	
	public Timestamp getDate(String table, String target, Target methods) {
		
		return this.getTs(table, target, "date", methods);
	}
	
	public boolean isSanctionned(String table, String target, String var, Target methods) {
		
		return this.Boolean(table, target, "ip", methods);
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
