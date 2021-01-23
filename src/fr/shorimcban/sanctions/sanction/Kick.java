package fr.shorimcban.sanctions.sanction;

import fr.shorimcban.sanctions.InstantSanction;

public class Kick extends InstantSanction{
	
	 private String Table = "KickManager";
	
	public Kick(String target) {
		
		super(target);
		this.table = Table;
	}
	
}
