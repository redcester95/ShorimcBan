package fr.shorimcban.sanctions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import fr.shorimcban.Main;
import fr.shorimcdatabase.DataBaseAPI;

public class InstantSanction extends Sanctions{
	
	
	public InstantSanction(String target) {
		
		super(target);
	}


	public void Sanction(String sender, String reason, Timestamp time)  {
		
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

	
	public void UnSanction(String senderUn, String reasonUn) {
		
		DataBaseAPI database = Main.getInstance().getDataBase();
		
		try {
			PreparedStatement ps = database.getConnection().prepareStatement("INSERT INTO " + untable + "(player_name, sender, unsender, reason , unreason, date, ip) VALUES (?,?,?,?,?,?)");
			ps.setString(1, target);
			ps.setString(2, this.getSender(Target.name));
			ps.setString(3, senderUn);
			ps.setString(4, this.getReason(Target.name));
			ps.setString(5, reasonUn);
			ps.setTimestamp(6, this.getDate(Target.name));
			ps.setString(7, Main.getInstance().getIP().getIp(target));
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
	
	
}
