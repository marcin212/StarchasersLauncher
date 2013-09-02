package pl.starchasers.launcher.launch;

import pl.starchasers.launcher.auth.Login;
import pl.starchasers.launcher.auth.Response;
import pl.starchasers.launcher.skin.components.ActionLabel;
import pl.starchasers.launcher.skin.components.LabelLaunch;
import pl.starchasers.launcher.skin.components.LoginTextField;
import pl.starchasers.launcher.skin.components.MyFrame;
import pl.starchasers.launcher.skin.components.PasswordTextField;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.json.MinecraftJson;
import pl.starchasers.launcher.utils.json.Version;

public class Launch {
	public static String name = "";
	private static String token ="";

	public static void login() {
		ActionLabel.instance.setAction("logging in...");
		LabelLaunch.instance.setText("Logging in");
		Response response = Login.loginInWithToken(Config.instance
				.getProperty("accessToken"));

		if (response != null) {
			ActionLabel.instance.setAction("launch minecraft!");
			LabelLaunch.instance.setText("Launch");
			Login.setCanRun(true);
			Login.setStatus(false);
			token = "token:" + response.getAccessToken() + ":"
					+ response.getSelectedProfile().getId();
			name = response.getSelectedProfile().getName();
		} else {
			ActionLabel.instance.setAction("Log in");
			LabelLaunch.instance.setText("Log in");
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
			ActionLabel.instance.setAction("launch minecraft!");
			LabelLaunch.instance.setText("Launch");
			Login.setCanRun(true);
			Login.setStatus(false);
		}
	}

	public static void runMinecraft() {
		new MinecraftJson();
		Version ver = MinecraftJson.instance.getObjForVersion("1.6.2");
		ActionLabel.instance.setAction("checking files...");
		new DownloadResources();
		// new CheckFiles();
		ActionLabel.instance.setAction("launching minecraft...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// new LaunchWrapper(token,ver,name);
	}
}
