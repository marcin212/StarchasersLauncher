package pl.starchasers.launcher.launch;

import java.util.HashMap;

import javax.swing.JComponent;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.auth.Login;
import pl.starchasers.launcher.auth.Response;
import pl.starchasers.launcher.skin.components.ActionLabel;
import pl.starchasers.launcher.skin.components.LoginTextField;
import pl.starchasers.launcher.skin.components.PasswordTextField;
import pl.starchasers.launcher.skin.components.SuperButton;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.json.MinecraftJson;
import pl.starchasers.launcher.utils.json.Version;

public class Launch {
	public static String name = "";
	public static HashMap<String, JComponent> elements = Main.getFrame().getPanel().getElements();  
	public static String token ="";

	public static void login() {
		((ActionLabel) elements.get("ACTIONLABEL")).setAction("logging in...");
		((SuperButton) elements.get("LAUNCH")).setTextLabel("Logging in");
		Response response = Login.loginInWithToken(Config.instance
				.getProperty("accessToken"));

		if (response != null) {
			((ActionLabel) elements.get("ACTIONLABEL")).setAction("launch minecraft!");
			((SuperButton) elements.get("LAUNCH")).setTextLabel("Launch");
			Login.setCanRun(true);
			Login.setStatus(false);
			token = "token:" + response.getAccessToken() + ":"
					+ response.getSelectedProfile().getId();
			name = response.getSelectedProfile().getName();
		} else {
			((ActionLabel) elements.get("ACTIONLABEL")).setAction("Log in");
			((SuperButton) elements.get("LAUNCH")).setTextLabel("Log in");
			Login.setStatus(true);
		}

	}

	public static void runButton() {

		@SuppressWarnings("deprecation")
		Response response = Login.loginInWithPassword(
				LoginTextField.instance.getText(),
				PasswordTextField.instance.getText());
		if (response != null) {
			token = "token:" + response.getAccessToken() + ":"
					+ response.getSelectedProfile().getId();
			name = response.getSelectedProfile().getName();
			((ActionLabel) elements.get("ACTIONLABEL")).setAction("launch minecraft!");
			((SuperButton) elements.get("LAUNCH")).setTextLabel("Launch");
			Login.setCanRun(true);
			Login.setStatus(false);
		}
	}

	public static void runMinecraft() {
		new MinecraftJson();
		Version ver = MinecraftJson.instance.getObjForVersion("1.6.2");
		((ActionLabel) elements.get("ACTIONLABEL")).setAction("checking files...");
		new DownloadResources();
		new CheckFiles();
		((ActionLabel) elements.get("ACTIONLABEL")).setAction("launching minecraft...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new LaunchWrapper(token,ver,name);
		Config.instance.setProperty("forceupdate","false");
	}
}
