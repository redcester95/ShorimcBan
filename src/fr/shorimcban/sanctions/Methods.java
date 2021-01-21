package fr.shorimcban.sanctions;

import java.sql.Timestamp;

public class Methods {
	

	public static class Kick extends InstantSanction {
		
		
		public void Sanction(String target, String sender, String reason) {
			
			super.Sanction(target, sender, reason, "KickManager", null);
		}
	}
	
	public static class Ban extends InstantSanction {
		
		public void Sanction(String target, String sender, String reason) {
			
			super.Sanction(target, sender, reason, "BanManager", null);
		}
		
		
		public void UnSanction(String target, String senderUn, String reasonUn) {
			
			super.UnSanction("UnBanManager", "BanManager", target, senderUn, reasonUn);
		}
	}
	
	public static class TempBan extends TempSanction {
		
		
		public void Sanction(String target, String sender, String reason, String table, Timestamp time) {
			
			super.Sanction(target, sender, reason, "TempBanManager", time);
		}
		
		
		public void UnSanction(String tableIn, String tableOut, String target, String senderUn, String reasonUn) {
			
			super.UnSanction("UnTempBanManager", "TempBanManager", target, senderUn, reasonUn);
		}
		
		public void SanctionExpired(String tableIn, String tableOut, String target) {
			
			super.SanctionExpired("UnTempMuteManager", "TempMuteManager", target);
		}
	}
	
	public static class TempMute extends TempSanction {
		
		public void Sanction(String target, String sender, String reason, String table, Timestamp time) {
			
			super.Sanction(target, sender, reason, "TempMuteManager", time);
		}
		
		public void UnSanction(String tableIn, String tableOut, String target, String senderUn, String reasonUn) {
			
			super.UnSanction("UnTempMuteManager", "TempMuteManager", target, senderUn, reasonUn);
		}
		
		
		public void SanctionExpired(String tableIn, String tableOut, String target) {
			
			super.SanctionExpired("UnTempMuteManager", "TempMuteManager", target);
		}
	}

}
