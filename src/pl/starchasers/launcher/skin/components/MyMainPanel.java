package pl.starchasers.launcher.skin.components;

import javax.swing.JPanel;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.skin.frame.playerskin.PlayerSkin;
import pl.starchasers.launcher.skin.frame.playerskin.labelHello;


public class MyMainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static MyMainPanel instance;

	public MyMainPanel() {
		instance = this;
		setBounds(0, 0, 854, 480);
		setLayout(null);
		add(new LabelLogout());
		add(new LogoutButton());
		add(new PlayerSkin());
		add(new labelHello());
		add(new CopyrightLabel());
		add(new ActionLabel());
		add(new MyProgressBar());
		add(new OptionsButton());
		add(new ProfileList(Main.profiles));
		add(new ManageProfilesButton());
		//add(new CheckBox());
		add(new WebSiteButton());
		add(new ButtonClose());
		add(new LabelLaunch());
		add(new LaunchButton());
		add(new PasswordTextField());
		add(new BgPasswordTextField());
		add(new LoginTextField());
		add(new BgUserNameTextField());
		add(new BackGroundLabel());
		
	}

}
