package fr.shorimcban.sanctions.sanction;

import fr.shorimcban.sanctions.TempSanction;

public class TempMute extends TempSanction{

	private String Table = "TempMuteManager";
	private String TableUn = "UnTempMuteManager";
	
	public TempMute(String target) {
		
		super(target);
		this.table = Table;
		this.untable = TableUn;
	}
}
