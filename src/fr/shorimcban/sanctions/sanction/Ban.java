package fr.shorimcban.sanctions.sanction;

import fr.shorimcban.sanctions.InstantSanction;

public class Ban extends InstantSanction {
	
	private String Table = "BanManager";
	private String TableUn = "UnBanManager";
	
	public Ban(String target) {
		
		super(target);
		this.table = Table;
		this.untable = TableUn;

	}
	

}
