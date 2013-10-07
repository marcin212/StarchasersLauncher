package pl.starchasers.launcher.launch;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.auth.Login;
import pl.starchasers.launcher.auth.Response;
import pl.starchasers.launcher.skin.panels.Contents;
import pl.starchasers.launcher.utils.json.MinecraftJson;
import pl.starchasers.launcher.utils.json.Version;

public class Launch {
	public static String name = "";
	public static Contents elements = Main.getFrame().getPanel();  
	public static String token ="";

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
		new MinecraftJson();
		Version ver = MinecraftJson.instance.getObjForVersion("1.6.2");
		elements.getActionLabel().setAction("checking files...");
		new DownloadResources();
		new CheckFiles();
		elements.getActionLabel().setAction("launching minecraft...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new LaunchWrapper(token,ver,name);
		Main.getConf().setProperty("forceupdate","false");
	}
}
