package pl.starchasers.launcher.profiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import pl.starchasers.launcher.utils.Http;
import pl.starchasers.launcher.utils.json.MinecraftJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class ProfileManager {
	private HashMap<String, Profile> profileslist = new HashMap<String, Profile>();
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public ProfileManager() {
		loadProfiles();
	}
	public Profile getProfileByName(String name){
		return profileslist.get(name);
	}

	public void saveProfiles() {
		try {
			FileWriter writer = new FileWriter("./config/profiles.json");
			writer.write(gson.toJson(profileslist));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadProfiles() {
		try {
			String content = "";
			File f = new File("./config/profiles.json");
			if (!f.exists())
				saveProfiles();
			Scanner reader = new Scanner(f);
			while (reader.hasNextLine())
				content += reader.nextLine();
			profileslist = gson.fromJson(content,
					new TypeToken<HashMap<String, Profile>>() {
					}.getType());
			reader.close();
			if (profileslist == null || profileslist.size() == 0) {
				profileslist = new HashMap<String, Profile>();
				defaultProfile();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			System.out.println(e.getMessage());
		}

	}

	public void defaultProfile() {
		Profile defaultprofile = new Profile();
		defaultprofile.setMinecraftversion(new MinecraftJson()
				.getCurrentVersion());
		defaultprofile.setXms("128M");
		defaultprofile.setXmx("512M");
		defaultprofile.setJvmargs(null);
		defaultprofile.setPermgen(null);
		defaultprofile.setSyncserver(null);
		profileslist.put("default",defaultprofile);
		saveProfiles();
	}

	public void addProfile(String name, Profile profile) {
		profileslist.put(name, profile);
		saveProfiles();
	}
	public void addProfile(HashMap<String, Profile> map) {
		profileslist.putAll(map);
		saveProfiles();
	}

	public void getProfileFromURL(String url) {
		String response = Http.executeHttpRequest(url, "", "GET",
				"application/json");
		HashMap<String, Profile> profile = gson.fromJson(response, new TypeToken<HashMap<String,Profile>>(){}.getType());
		profileslist.putAll(profile);
		saveProfiles();
	}
	
	public void deleteProfile(String name){
		profileslist.remove(name);
		saveProfiles();
		
	}

	public HashMap<String, Profile> getProfileslist() {
		return profileslist;
	}

	public void setProfileslist(HashMap<String, Profile> profileslist) {
		this.profileslist = profileslist;
	}
	
	public void refreshRemoteProfile(Profile profile){
		String url  = profile.getSyncserver() + "profile.json";
		String response = Http.executeHttpRequest(url, "", "GET","application/json");
		HashMap<String, Profile> remoteProfile = gson.fromJson(response, new TypeToken<HashMap<String,Profile>>(){}.getType());
		for(Entry<String, Profile> p : remoteProfile.entrySet()){
		System.out.println("forgeR"+p.getValue().getForgeName());
		profile.setForgeName(p.getValue().getForgeName());
		profile.setMinecraftversion(profile.getMinecraftversion());
		}
		saveProfiles();
	}

}
