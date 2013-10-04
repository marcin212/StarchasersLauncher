package pl.starchasers.launcher.skin.panels;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.skin.components.ButtonClose;
import pl.starchasers.launcher.skin.components.LaunchButton;
import pl.starchasers.launcher.skin.components.LoginTextField;
import pl.starchasers.launcher.skin.components.OptionsButton;
import pl.starchasers.launcher.skin.components.PasswordTextField;
import pl.starchasers.launcher.skin.components.ProfileList;
import pl.starchasers.launcher.skin.components.WebSiteButton;
import pl.starchasers.launcher.skin.frame.playerskin.PlayerSkin;
import pl.starchasers.launcher.skin.frame.playerskin.labelHello;
import pl.starchasers.launcher.utils.Variable;


public class Contents extends JLayeredPane {
	private static final long serialVersionUID = 1L;
	public  BgLayer iconLayer = new BgLayer();
	private JLabel  bg;
	private Component buttonClose;
	private Component buttonOptions;
	private Component buttonWeb;
	private Component buttonLaunch;
	private Component listProfile;
	private Component textFieldUsername;
	private Component textfieldPassword;
	HashMap<String, JComponent> components = new HashMap<String, JComponent>();
	public Contents() {
		setBounds(0, 0, 854, 480);
		setLayout(null);
	
		add(buttonClose = new ButtonClose());
		add(buttonOptions = new OptionsButton());
		add(buttonWeb = new WebSiteButton());
		add(buttonLaunch = new LaunchButton(this));
		add(listProfile = new ProfileList(Main.profiles));
		add(textFieldUsername = new LoginTextField(iconLayer));
		add(textfieldPassword = new PasswordTextField());
		setBackground();
//		newElement("LOGOUT", new LogoutButton(BGLayer));
//		newElement("PLAYER_SKIN", new PlayerSkin());
//		newElement("LABEL_HELLO", new labelHello());
//		newElement("COPYRIGHT", new CopyrightLabel());
//		newElement("ACTIONLABEL", new ActionLabel());
//		newElement("PROGRESSBAR", new MyProgressBar());
//	
//
//	//	add(new ManageProfilesButton());
//		//add(new CheckBox());
//	
//		
//	
//		newElement("PASSWORD", new PasswordTextField());
//		//newElement("BG_PASSWD", new BgPasswordTextField());
//		newElement("LOGIN", new LoginTextField(BGLayer));
//		//newElement("BG_LOGIN", new BgUserNameTextField());
//		BGLayer.add(new BackGroundLabel());
//	//	newElement("BACKGROUND",new BackGroundLabel());
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
	private void setBackground(){
		bg = new JLabel();
		bg.setBackground(Color.BLACK);
		bg.setHorizontalAlignment(SwingConstants.CENTER);
		bg.setIcon(new ImageIcon(BgLayer.class.getResource(Variable.resourcePath+"background.png")));
		bg.setBounds(0, 0, 854, 480);
		iconLayer.add(bg);
	}

}
