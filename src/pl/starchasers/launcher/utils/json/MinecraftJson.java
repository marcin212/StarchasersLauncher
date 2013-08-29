package pl.starchasers.launcher.utils.json;

import pl.starchasers.launcher.utils.Config;

import com.google.gson.Gson;


public class MinecraftJson {
	public static MinecraftJson instance;
	private static ForgeLibs libsforge;

	public final String urlVersion = "http://s3.amazonaws.com/Minecraft.Download/versions/versions.json";
	private Versions obj;
	private final String urlFiles = "https://s3.amazonaws.com/Minecraft.Download/versions/";
	public MinecraftJson(){
		instance = this;
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
		addForgeLibraries(ver);
		return ver;
	}
	public void addForgeLibraries(Version ver){
		Gson gson = new Gson();
		String json = Http.excuteGet(Config.instance.getProperty("sync-server")+"forgelibs.json");
		libsforge = gson.fromJson(json, ForgeLibs.class);
		
		for(int i=0;i<libsforge.getLibs().size();i++){
			ver.getLibraries().add(libsforge.getLibs().get(i));	
		}
	}	
	public ForgeLibs getLibsForge() {
		return libsforge;
	}


	public void setLibsForge(ForgeLibs libs) {
		MinecraftJson.libsforge = libs;
	}
}
