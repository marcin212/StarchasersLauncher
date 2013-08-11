package pl.starchasers.launcher.utils.json;

import java.util.List;

public class Version{
   	private String id;
   	private List<Libraries> libraries;
   	private String mainClass;
   	private String minecraftArguments;
   	private Number minimumLauncherVersion;
   	private String processArguments;
   	private String releaseTime;
   	private String time;
   	private String type;

 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public List<Libraries> getLibraries(){
		return this.libraries;
	}
	public void setLibraries(List<Libraries> libraries){
		this.libraries = libraries;
	}
 	public String getMainClass(){
		return this.mainClass;
	}
	public void setMainClass(String mainClass){
		this.mainClass = mainClass;
	}
 	public String getMinecraftArguments(){
		return this.minecraftArguments;
	}
	public void setMinecraftArguments(String minecraftArguments){
		this.minecraftArguments = minecraftArguments;
	}
 	public Number getMinimumLauncherVersion(){
		return this.minimumLauncherVersion;
	}
	public void setMinimumLauncherVersion(Number minimumLauncherVersion){
		this.minimumLauncherVersion = minimumLauncherVersion;
	}
 	public String getProcessArguments(){
		return this.processArguments;
	}
	public void setProcessArguments(String processArguments){
		this.processArguments = processArguments;
	}
 	public String getReleaseTime(){
		return this.releaseTime;
	}
	public void setReleaseTime(String releaseTime){
		this.releaseTime = releaseTime;
	}
 	public String getTime(){
		return this.time;
	}
	public void setTime(String time){
		this.time = time;
	}
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
}
