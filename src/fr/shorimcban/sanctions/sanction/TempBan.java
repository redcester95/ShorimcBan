package fr.shorimcban.sanctions.sanction;

import fr.shorimcban.sanctions.TempSanction;

public class TempBan extends TempSanction {
	
	private String Table = "TempBanManager";
	private String TableUn = "UnTempBanManager";

	public TempBan(String target) {
		
		super(target);
		this.table = Table;
		this.untable = TableUn;
	}	
}
