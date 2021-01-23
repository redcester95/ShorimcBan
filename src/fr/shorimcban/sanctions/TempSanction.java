package fr.shorimcban.sanctions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import fr.shorimcban.Main;
import fr.shorimcdatabase.DataBaseAPI;

public class TempSanction extends Sanctions{

	
	public TempSanction(String target) {
		
		super(target);
	}


	public void Sanction(String sender, String reason, Timestamp time) {
		
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

	
	public void UnSanction(String senderUn, String reasonUn) {
		
		DataBaseAPI database = Main.getInstance().getDataBase();
		
		try {
			PreparedStatement ps = database.getConnection().prepareStatement("INSERT INTO " + untable + "(player_name, sender, unsender, reason, unreason, date, time, ip) VALUES (?,?,?,?,?,?,?,?)");
			ps.setString(1, target);
			ps.setString(2, this.getSender(Target.name));
			ps.setString(3, senderUn);
			ps.setString(4, super.getReason(Target.name));
			ps.setString(5, reasonUn);
			ps.setTimestamp(6, this.getDate(Target.name));
			ps.setTimestamp(7, this.getTime(Target.name));
			ps.setString(8, Main.getInstance().getIP().getIp(target));
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps = database.getConnection().prepareStatement("DELETE FROM " + table + " WHERE player_name = ?");
			ps.setString(1, target);
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void SanctionExpired() {
		
		DataBaseAPI database = Main.getInstance().getDataBase();
		
		try {
			PreparedStatement ps = database.getConnection().prepareStatement("INSERT INTO " + untable + "(player_name, sender, unsender, reason, unreason, date, time, ip) VALUES (?,?,?,?,?,?,?,?)");
			ps.setString(1, target);
			ps.setString(2, super.getSender(Target.name));
			ps.setString(3, "machine");
			ps.setString(4, super.getReason(Target.name));
			ps.setString(5, "expired");
			ps.setTimestamp(6, super.getDate(Target.name));
			ps.setTimestamp(7, this.getTime(Target.name));
			ps.setString(8, Main.getInstance().getIP().getIp(target));
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps = database.getConnection().prepareStatement("DELETE FROM " + table + " WHERE player_name = ?");
			ps.setString(1, target);
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Timestamp getTime(Target methods) {
		
		return super.getTs("time", Target.name);
	}


	

	
}
