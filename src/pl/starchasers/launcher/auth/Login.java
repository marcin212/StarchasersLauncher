package pl.starchasers.launcher.auth;


import java.io.IOException;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.skin.frame.playerskin.PlayerSkin;
import pl.starchasers.launcher.skin.frame.playerskin.labelHello;
import pl.starchasers.launcher.skin.panels.Contents;
import pl.starchasers.launcher.utils.Http;
import pl.starchasers.launcher.utils.Variable;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


public class Login {
	private final static String urlauthentication = "https://authserver.mojang.com/authenticate";
	private final static String urlrefresh= "https://authserver.mojang.com/refresh";
	private final static Gson gson = new Gson();
	public static Contents elements = Main.getFrame().getPanel(); 
	private static Boolean status = false;
	private static Boolean canRun = false;
	public static Response loginInWithPassword(String name, String passwd){
		JsonLogin payload = new JsonLogin();
		payload.setUsername(name);
		payload.setPassword(passwd);
		
		Response response = null;
		try {
			response = gson.fromJson(Http.executeHttpRequestE(urlauthentication, gson.toJson(payload), "POST", "application/json"), Response.class);
			Main.getConf().setProperty("clientToken",response.getClientToken());
			Main.getConf().setProperty("playerUUID", response.getSelectedProfile().getId());
			Main.getConf().setProperty("accessToken", response.getAccessToken());			
			Main.getConf().setProperty("nickname", response.getSelectedProfile().getName());	
			Main.getConf().store(Variable.propertiesPath);			
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
		query.setAccessToken(Main.getConf().getProperty("accessToken"));
		query.setClientToken(Main.getConf().getProperty("clientToken"));
		Response response = null;
		try {
			response = gson.fromJson(Http.executeHttpRequestE(urlrefresh, gson.toJson(query), "POST", "application/json"), Response.class);
			Main.getConf().setProperty("accessToken", response.getAccessToken());
			Main.getConf().setProperty("nickname", response.getSelectedProfile().getName());	
			Main.getConf().store(Variable.propertiesPath);
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
		elements.getTextfieldPassword().setVisible(visible);
		elements.getTextfieldPassword().getLabeltextfield().setVisible(visible);
		elements.getTextFieldUsername().setVisible(visible);
		elements.getTextFieldUsername().getLabeltextfield().setVisible(visible);
		elements.getPlayerSkin().setVisible(!visible);
		elements.getLabelHello().setVisible(!visible);
		elements.getButtonLogOut().setVisible(!visible);
		elements.getButtonLogOut().getButtonLabel().setVisible(!visible);
	}
	public static Boolean getCanRun() {
		return canRun;
	}
	public static void setCanRun(Boolean canRun) {
		Login.canRun = canRun;
	}
}
