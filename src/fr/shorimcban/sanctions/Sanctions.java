package fr.shorimcban.sanctions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import fr.shorimcban.Main;

public abstract class Sanctions{
	
	protected String table;
	protected String untable;
	protected String target;
	
	public Sanctions(String target) {
		
		this.target = target;
	}
	
	public abstract void Sanction(String sender, String reason, Timestamp time);
	
	public abstract void UnSanction(String sender, String reason);
	
	protected String getStr(String var,Target methods) {
		
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
	
	protected Timestamp getTs(String var,Target methods) {
		
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
	
	protected boolean Boolean(String var, Target methods) {
		
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
	
	public String getSender(Target methods) {
		
		return this.getStr("sender", methods);
	}
	
	public String getReason(Target methods) {
		
		return this.getStr("reason", methods);
	}
	
	public Timestamp getDate(Target methods) {
		
		return this.getTs("date", methods);
	}
	
	public boolean isSanctionned(Target methods) {
		
		return this.Boolean("ip", methods);
	}
	
}
