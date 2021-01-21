package fr.shorimcban.sanctions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import fr.shorimcban.Main;
import fr.shorimcdatabase.DataBaseAPI;

public abstract class InstantSanction extends Sanctions{
	
	
	protected void Sanction(String target, String sender, String reason, String table, Timestamp time)  {
		
		DataBaseAPI database = Main.getInstance().getDataBase();
		try {
			PreparedStatement ps = database.getConnection().prepareStatement("INSERT INTO " + table + "(player_name, reason, sender, ip) VALUES (?,?,?,?)");
			ps.setString(1, target);
			ps.setString(2, reason);
			ps.setString(3, sender);
			ps.setString(4, Main.getInstance().getIP().getIp(target));
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
			PreparedStatement ps = database.getConnection().prepareStatement("INSERT INTO " + tableIn + "(player_name, sender, senderUN, reason ,reasonUN, date, ip) VALUES (?,?,?,?,?,?)");
			ps.setString(1, target);
			ps.setString(2, this.getStr(tableOut, target, "sender", Target.name));
			ps.setString(3, senderUn);
			ps.setString(4, this.getStr(tableOut, target, "reason", Target.name));
			ps.setString(5, reasonUn);
			ps.setTimestamp(6, this.getTs(tableOut, target, "date", Target.name));
			ps.setString(7, Main.getInstance().getIP().getIp(target));
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
	
	
}
