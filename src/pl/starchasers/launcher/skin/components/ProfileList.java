package pl.starchasers.launcher.skin.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JComboBox;

import pl.starchasers.launcher.profiles.Profile;
import pl.starchasers.launcher.profiles.ProfileManager;
import pl.starchasers.launcher.skin.MyFont;

public class ProfileList extends JComboBox<Object> {
	private static final long serialVersionUID = 1L;
	
	public ProfileList(ProfileManager profile) {
		super(init(profile).toArray());
		setBounds(510, 355, 97, 23);
		setForeground(new Color(84, 91, 100));
		setFont(new MyFont().returnFont());

		
	}

	public static ArrayList<String> init(ProfileManager profile) {
		ArrayList<String> list = new ArrayList<String>();
		
		for(Entry<String,Profile> act:profile.getProfileslist().entrySet()) {
			list.add(act.getKey());
			System.out
					.println(act.getKey());
			
		}
		return list;
	}

	
}
