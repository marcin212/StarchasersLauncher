package pl.starchasers.launcher.auth;


import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import pl.starchasers.launcher.skin.components.BgPasswordTextField;
import pl.starchasers.launcher.skin.components.BgUserNameTextField;
import pl.starchasers.launcher.skin.components.LoginTextField;
import pl.starchasers.launcher.skin.components.PasswordTextField;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.Http;
import pl.starchasers.launcher.utils.Variable;


public class Login {
	private final static String urlauthentication = "https://authserver.mojang.com/authenticate";
	private final static String urlrefresh= "https://authserver.mojang.com/refresh";
	private final static Gson gson = new Gson();
	private static Boolean status = false;
	private static Boolean canRun = false;
	public static Response loginInWithPassword(String name, String passwd){
		JsonLogin payload = new JsonLogin();
		payload.setUsername(name);
		payload.setPassword(passwd);
		
		Response response = null;
		try {
			response = gson.fromJson(Http.executeHttpRequestE(urlauthentication, gson.toJson(payload), "POST", "application/json"), Response.class);
			Config.instance.setProperty("clientToken",response.getClientToken());
			Config.instance.setProperty("playerUUID", response.getSelectedProfile().getId());
			Config.instance.setProperty("accessToken", response.getAccessToken());			
			Config.instance.store(Variable.propertiesPath);			
		
		} catch (JsonSyntaxException | IOException  e) {
			
			e.printStackTrace();
		}
		return response;		
	}
	public static Response loginInWithToken(String token){
		hiddenTextfield(false);
		Response query = new Response();
		query.setAccessToken(Config.instance.getProperty("accessToken"));
		query.setClientToken(Config.instance.getProperty("clientToken"));
		Response response = null;
		try {
			response = gson.fromJson(Http.executeHttpRequestE(urlrefresh, gson.toJson(query), "POST", "application/json"), Response.class);
			Config.instance.setProperty("accessToken", response.getAccessToken());
			Config.instance.store(Variable.propertiesPath);
		} catch (JsonSyntaxException | IOException e) {
			setStatus(true);
			hiddenTextfield(true);
		}
		return response;
	}
	public static Boolean getStatus() {
		return status;
	}
	public static void setStatus(Boolean status) {
		Login.status = status;
	}
	public static void hiddenTextfield(Boolean visible){
		PasswordTextField.instance.setVisible(visible);
		LoginTextField.instance.setVisible(visible);
		BgUserNameTextField.instance.setVisible(visible);
		BgPasswordTextField.instance.setVisible(visible);
	}
	public static Boolean getCanRun() {
		return canRun;
	}
	public static void setCanRun(Boolean canRun) {
		Login.canRun = canRun;
	}
}
