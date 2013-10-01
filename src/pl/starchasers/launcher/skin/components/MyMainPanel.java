package pl.starchasers.launcher.skin.components;

import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JPanel;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.skin.frame.playerskin.PlayerSkin;
import pl.starchasers.launcher.skin.frame.playerskin.labelHello;


public class MyMainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static MyMainPanel instance;
	HashMap<String, JComponent> components = new HashMap<String, JComponent>();
	public MyMainPanel() {
		instance = this;
		setBounds(0, 0, 854, 480);
		setLayout(null);
		newElement("LOGOUT", new LogoutButton(this));
		newElement("PLAYER_SKIN", new PlayerSkin());
		newElement("LABEL_HELLO", new labelHello());
		newElement("COPYRIGHT", new CopyrightLabel());
		newElement("ACTIONLABEL", new ActionLabel());
		newElement("PROGRESSBAR", new MyProgressBar());
		newElement("OPTIONS", new OptionsButton());
		newElement("PROFILES", new ProfileList(Main.profiles));
	//	add(new ManageProfilesButton());
		//add(new CheckBox());
		newElement("WEB", new WebSiteButton());
		newElement("CLOSE", new ButtonClose());
		newElement("LAUNCH", new LaunchButton(this));
		newElement("PASSWORD", new PasswordTextField());
		newElement("BG_PASSWD", new BgPasswordTextField());
		newElement("LOGIN", new LoginTextField());
		newElement("BG_LOGIN", new BgUserNameTextField());	
		newElement("BACKGROUND",new BackGroundLabel());
	}
	public void newElement(String name, JComponent comp){
		components.put(name,comp);
		add(components.get(name));
	}
	public HashMap<String, JComponent> getElements() {
		return components;
	}
	public void setElements(HashMap<String, JComponent> components) {
		this.components = components;
	}
	

}
