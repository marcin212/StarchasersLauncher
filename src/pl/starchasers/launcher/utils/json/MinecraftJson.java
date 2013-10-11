package pl.starchasers.launcher.utils.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.google.gson.Gson;


public class MinecraftJson {
	public static MinecraftJson instance;
	private static Version libsforge;

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
		return ver;
	}
	public Version getObjForVersion(String version,String forgename){
		Gson gson = new Gson();
		String json = Http.excuteGet(urlFiles+version+"/"+version+".json");
		Version ver = gson.fromJson(json,Version.class);
		addForgeLibraries(ver,forgename);
		return ver;
	}
	public void addForgeLibraries(Version ver, String forgename){
		Scanner in = null;
		String content = "";
		try {
			Gson gson = new Gson();
			String[] parts= forgename.split(":");
			File file = new File("./starchasers/minecraft/libraries/"+parts[0].replace('.', '/') + "/" + parts[1] + "/" + parts[2] + "/version.json");
			if(!file.exists())
				 return;
			in = new Scanner(file);
			while(in.hasNext()){
				 content =content + in.nextLine();
			}
			System.out.println("test:"+content);
			libsforge = gson.fromJson(content, Version.class);
			for(int i=0;i<libsforge.getLibraries().size();i++){
				ver.getLibraries().add(libsforge.getLibraries().get(i));	
			}
			ver.setMainClass(libsforge.getMainClass());
			ver.setMinecraftArguments(libsforge.getMinecraftArguments());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			in.close();
		}
	}	
	
	public Version getLibsForge() {
		return libsforge;
	}


	public void setLibsForge(Version libs) {
		MinecraftJson.libsforge = libs;
	}
}
