package pl.starchasers.launcher.profiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import pl.starchasers.launcher.utils.json.MinecraftJson;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class ProfileManager {
	private static ArrayList<Profile> profileslist = new ArrayList<Profile>();
	private Gson gson = new Gson();

	public ProfileManager() {
		loadProfiles();
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
			if(!f.exists()) saveProfiles();
			Scanner reader = new Scanner(f);
			while (reader.hasNextLine())
				content += reader.nextLine();
			profileslist = gson.fromJson(content,new TypeToken<ArrayList<Profile>>(){}.getType());
			reader.close();
			if(profileslist==null || profileslist.size()==0){
				profileslist =new ArrayList<Profile>();
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
		defaultprofile.setMinecraftversion(new MinecraftJson().getCurrentVersion());
		defaultprofile.setXms("128M");
		defaultprofile.setXmx("512M");
		defaultprofile.setProfilname("Default");
		defaultprofile.setJvmargs(null);
		defaultprofile.setPermgen(null);
		defaultprofile.setSyncserver(null);
		profileslist.add(defaultprofile);
		saveProfiles();
	}
}
