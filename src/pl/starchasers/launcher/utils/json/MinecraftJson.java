package pl.starchasers.launcher.utils.json;

import com.google.gson.Gson;


public class MinecraftJson {
	public final String urlVersion = "http://s3.amazonaws.com/Minecraft.Download/versions/versions.json";
	private Versions obj;
	private final String urlFiles = "https://s3.amazonaws.com/Minecraft.Download/versions/";
	public MinecraftJson(){
		Gson gson = new Gson();
		String json = Http.excuteGet(urlVersion);
		obj = gson.fromJson(json,Versions.class);
	}
	
	
	public String getCurrentVersion(){
		return obj.getLatest().getRelease();
	}
	public Versions getVersions(){
		return this.obj;
	}
	public Version getObjForVersion(String version){
		Gson gson = new Gson();
		String json = Http.excuteGet(urlFiles+version+"/"+version+".json");
		Version ver = gson.fromJson(json,Version.class);
		return ver;
	}
}
