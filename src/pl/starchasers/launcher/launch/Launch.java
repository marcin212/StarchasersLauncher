package pl.starchasers.launcher.launch;

import pl.starchasers.launcher.skin.components.LoginTextField;
import pl.starchasers.launcher.skin.components.PasswordTextField;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.Http;
import pl.starchasers.launcher.utils.json.JsonLogin;
import pl.starchasers.launcher.utils.json.MinecraftJson;
import pl.starchasers.launcher.utils.json.Version;

import com.google.gson.Gson;

public class Launch {
	public String token = "";
	public Launch(){
		Gson gson = new Gson();
		JsonLogin payload = new JsonLogin();
		payload.setUsername(LoginTextField.instance.getText());
		payload.setPassword(PasswordTextField.instance.getText());
		payload.setClientToken(Config.instance.getProperty("clientToken"));
		String pts = gson.toJson(payload);
		String response = Http.executeHttpRequest("https://authserver.mojang.com/authenticate", pts, "POST", "application/json");
		Response resp = gson.fromJson(response, Response.class);
		token = "token:" + resp.getAccessToken() + ":" + resp.getSelectedProfile().getId();
		new MinecraftJson();
		Version ver = MinecraftJson.instance.getObjForVersion("1.6.2");
		new CheckFiles();
		new LaunchWrapper(token,ver);
	}
}
