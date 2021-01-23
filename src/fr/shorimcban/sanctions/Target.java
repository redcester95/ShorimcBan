package fr.shorimcban.sanctions;

public enum Target {
	
	ip("ip"), 
	name("target");
	
	private String methods = "";

	private Target(String methods) {
		this.methods  = methods;
	}
	
	
	public String toString() {
		return methods;
	}	

}
