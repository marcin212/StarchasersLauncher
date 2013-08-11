package pl.starchasers.launcher.skin.components;

import javax.swing.JPanel;

public class MyMainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static MyMainPanel instance;
/*	private MyProgressBar progressBar;
	private OptionsButton optionsButton;
	private CheckBox checkBox;
	private WebSiteButton webButton;
	private ButtonClose closeButton;
	private LabelLaunch launchLabel;
	private LaunchButton launchButton;
	private PasswordTextField passwordField;
	private BgPasswordTextField bgPasswordTextField;
	private LoginTextField loginField;
	private BgUserNameTextField bgUserNameTextField;
	private BackGroundLabel backGround;*/

	public MyMainPanel() {
		instance = this;
		setBounds(0, 0, 854, 480);
		setLayout(null);
		add(new MyProgressBar());
		add(new OptionsButton());
		add(new CheckBox());
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
