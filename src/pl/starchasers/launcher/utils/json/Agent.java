package pl.starchasers.launcher.utils.json;

public class Agent {
	private String name = "Minecraft";
	private Number version = 1;
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	public Number getVersion(){
		return this.version;
	}
	public void setVersion(Number version){
		this.version = version;
	}
}
