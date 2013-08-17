package pl.starchasers.launcher.launch;

import java.util.UUID;

import pl.starchasers.launcher.skin.components.ActionLabel;
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
	public String name = "";
	@SuppressWarnings("deprecation")
	public Launch(){
	if(Config.instance.getProperty("non-premium").compareTo("false")==0){
		
		Gson gson = new Gson();
		JsonLogin payload = new JsonLogin();
		payload.setUsername(LoginTextField.instance.getText());
		payload.setPassword(PasswordTextField.instance.getText());
		payload.setClientToken(Config.instance.getProperty("clientToken"));
		String pts = gson.toJson(payload);
		ActionLabel.instance.setAction("logging in...");
		String response = Http.executeHttpRequest("https://authserver.mojang.com/authenticate", pts, "POST", "application/json");
		Response resp = gson.fromJson(response, Response.class);
		token = "token:" + resp.getAccessToken() + ":" + resp.getSelectedProfile().getId();
		name = resp.getSelectedProfile().getName();
	}else{
		token = UUID.randomUUID().toString();
		name = LoginTextField.instance.getText();
	}
		
		new MinecraftJson();
		Version ver = MinecraftJson.instance.getObjForVersion("1.6.2");
		ActionLabel.instance.setAction("checking files...");
		new DownloadResources();
		new CheckFiles();
		ActionLabel.instance.setAction("launching minecraft...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new LaunchWrapper(token,ver,name);
	}
}
