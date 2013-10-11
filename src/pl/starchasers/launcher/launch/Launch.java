package pl.starchasers.launcher.launch;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.auth.Login;
import pl.starchasers.launcher.auth.Response;
import pl.starchasers.launcher.profiles.Profile;
import pl.starchasers.launcher.skin.panels.Contents;
import pl.starchasers.launcher.sync.Sync;
import pl.starchasers.launcher.utils.json.MinecraftJson;

public class Launch {
	public static String name = "";
	public static Contents elements = Main.getFrame().getPanel();  
	public static String token ="";
	public static Profile currentProfile;

	public static void login() {
		elements.getActionLabel().setAction("logging in...");
		elements.getButtonLaunch().setTextLabel("Logging in");
		Response response = Login.loginInWithToken(Main.getConf().getProperty("accessToken"));

		if (response != null) {
			elements.getActionLabel().setAction("launch minecraft!");
			elements.getButtonLaunch().setTextLabel("Launch");
			Login.setCanRun(true);
			Login.setStatus(false);
			token = "token:" + response.getAccessToken() + ":"
					+ response.getSelectedProfile().getId();
			name = response.getSelectedProfile().getName();
		} else {
			elements.getActionLabel().setAction("Log in");
			elements.getButtonLaunch().setTextLabel("Log in");
			Login.setStatus(true);
		}

	}

	public static void runButton() {

		@SuppressWarnings("deprecation")
		Response response = Login.loginInWithPassword(
				elements.getTextFieldUsername().getText(),
				elements.getTextfieldPassword().getText());
		if (response != null) {
			token = "token:" + response.getAccessToken() + ":"
					+ response.getSelectedProfile().getId();
			name = response.getSelectedProfile().getName();
			elements.getActionLabel().setAction("launch minecraft!");
			elements.getButtonLaunch().setTextLabel("Launch");
			Login.setCanRun(true);
			Login.setStatus(false);
		}
	}

	public static void runMinecraft() {
		currentProfile = Main.getProfiles().getProfileByName(elements.getListProfile().getSelectedItem().toString());
		if (currentProfile.getSyncserver() == null || currentProfile.getSyncserver().length() ==0){
			}else{
			Main.getProfiles().refreshRemoteProfile(currentProfile);
			new Sync(currentProfile);
			}
			elements.getActionLabel().setAction("checking files...");
			new DownloadResources();
			new MinecraftJson();
			new CheckFiles(currentProfile);
			elements.getActionLabel().setAction("launching minecraft...");
			new LaunchWrapper(token,CheckFiles.ver,name,currentProfile);
			Main.getConf().setProperty("forceupdate","false");
	}

}
