package pl.starchasers.launcher.auth;


import java.io.IOException;
import java.util.HashMap;

import javax.swing.JComponent;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.skin.components.SuperButton;
import pl.starchasers.launcher.skin.frame.playerskin.PlayerSkin;
import pl.starchasers.launcher.skin.frame.playerskin.labelHello;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.Http;
import pl.starchasers.launcher.utils.Variable;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


public class Login {
	private final static String urlauthentication = "https://authserver.mojang.com/authenticate";
	private final static String urlrefresh= "https://authserver.mojang.com/refresh";
	private final static Gson gson = new Gson();
	public static HashMap<String, JComponent> elements = Main.getFrame().getPanel().getElements(); 
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
			Config.instance.setProperty("nickname", response.getSelectedProfile().getName());	
			Config.instance.store(Variable.propertiesPath);			
			PlayerSkin.instance.setSkin(response.getSelectedProfile().getName());
			labelHello.instance.setUserName(response.getSelectedProfile().getName());
			hiddenTextfield(false);
		} catch (JsonSyntaxException | IOException  e) {
			hiddenTextfield(true);
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
			Config.instance.setProperty("nickname", response.getSelectedProfile().getName());	
			Config.instance.store(Variable.propertiesPath);
			PlayerSkin.instance.setSkin(response.getSelectedProfile().getName());
			labelHello.instance.setUserName(response.getSelectedProfile().getName());
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
		elements.get("PASSWORD").setVisible(visible);
		elements.get("BG_PASSWD").setVisible(visible);
		elements.get("LOGIN").setVisible(visible);
		elements.get("BG_LOGIN").setVisible(visible);
		elements.get("PLAYER_SKIN").setVisible(!visible);
		elements.get("LABEL_HELLO").setVisible(!visible);
		elements.get("LOGOUT").setVisible(!visible);
		((SuperButton)elements.get("LOGOUT")).getButtonLabel().setVisible(!visible);
	}
	public static Boolean getCanRun() {
		return canRun;
	}
	public static void setCanRun(Boolean canRun) {
		Login.canRun = canRun;
	}
}
