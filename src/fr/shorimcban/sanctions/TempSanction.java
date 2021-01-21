package fr.shorimcban.sanctions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import fr.shorimcban.Main;
import fr.shorimcdatabase.DataBaseAPI;

public abstract class TempSanction extends Sanctions{

	
	protected void Sanction(String target, String sender, String reason, String table, Timestamp time) {
		
		DataBaseAPI database = Main.getInstance().getDataBase();		
		try {
			PreparedStatement ps = database.getConnection().prepareStatement("INSERT INTO " + table +"(player_name, sender, reason, time, ip) VALUES (?,?,?,?,?)");
			
			ps.setString(1, target);
			ps.setString(2, sender);
			ps.setString(3, reason);
			ps.setTimestamp(4, time);
			ps.setString(5, Main.getInstance().getIP().getIp(target));
			ps.execute();
			ps.close();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void UnSanction(String tableIn, String tableOut, String target, String senderUn, String reasonUn) {
		
		DataBaseAPI database = Main.getInstance().getDataBase();
		
		try {
			PreparedStatement ps = database.getConnection().prepareStatement("INSERT INTO " + tableIn + "(player_name, sender, senderUn, reason, reasonUn, date, time, ip) VALUES (?,?,?,?,?,?,?,?)");
			ps.setString(1, target);
			ps.setString(2, super.getSender(target, tableOut, Target.name));
			ps.setString(3, senderUn);
			ps.setString(4, super.getReason(target, tableOut, Target.name));
			ps.setString(5, reasonUn);
			ps.setTimestamp(6, super.getDate(tableOut, target, Target.name));
			ps.setTimestamp(7, this.getTime(target, tableOut, Target.name));
			ps.setString(8, Main.getInstance().getIP().getIp(target));
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps = database.getConnection().prepareStatement("DELETE FROM " + tableOut + " WHERE player_name = ?");
			ps.setString(1, target);
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected void SanctionExpired(String tableIn, String tableOut, String target) {
		
		DataBaseAPI database = Main.getInstance().getDataBase();
		
		try {
			PreparedStatement ps = database.getConnection().prepareStatement("INSERT INTO " + tableIn + "(player_name, sender, senderUn, reason, reasonUn, date, time, ip) VALUES (?,?,?,?,?,?,?,?)");
			ps.setString(1, target);
			ps.setString(2, super.getSender(target, tableOut, Target.name));
			ps.setString(3, "machine");
			ps.setString(4, super.getReason(target, tableOut, Target.name));
			ps.setString(5, "expired");
			ps.setTimestamp(6, super.getDate(tableOut, target, Target.name));
			ps.setTimestamp(7, this.getTime(target, tableOut, Target.name));
			ps.setString(8, Main.getInstance().getIP().getIp(target));
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps = database.getConnection().prepareStatement("DELETE FROM " + tableOut + " WHERE player_name = ?");
			ps.setString(1, target);
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Timestamp getTime(String target, String table,Target methods) {
		
		return super.getTs(table, target, "time", Target.name);
	}


	

	
}
