package pl.starchasers.launcher.skin.panels;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.skin.components.ActionLabel;
import pl.starchasers.launcher.skin.components.ButtonClose;
import pl.starchasers.launcher.skin.components.CopyrightLabel;
import pl.starchasers.launcher.skin.components.LaunchButton;
import pl.starchasers.launcher.skin.components.LoginTextField;
import pl.starchasers.launcher.skin.components.LogoutButton;
import pl.starchasers.launcher.skin.components.ManageProfilesButton;
import pl.starchasers.launcher.skin.components.MyProgressBar;
import pl.starchasers.launcher.skin.components.OptionsButton;
import pl.starchasers.launcher.skin.components.PasswordTextField;
import pl.starchasers.launcher.skin.components.ProfileList;
import pl.starchasers.launcher.skin.components.WebSiteButton;
import pl.starchasers.launcher.skin.frame.playerskin.PlayerSkin;
import pl.starchasers.launcher.skin.frame.playerskin.labelHello;
import pl.starchasers.launcher.utils.Variable;

public class Contents extends JLayeredPane {
	private static final long serialVersionUID = 1L;
	public BgLayer iconLayer = new BgLayer();
	private JLabel bg;
	private ButtonClose buttonClose;
	private OptionsButton buttonOptions;
	private WebSiteButton buttonWeb;
	private LaunchButton buttonLaunch;
	private ProfileList listProfile;
	private LoginTextField textFieldUsername;
	private PasswordTextField textfieldPassword;
	private LogoutButton buttonLogOut;
	private CopyrightLabel copyrights;
	private ActionLabel actionLabel;
	private MyProgressBar progressbar;
	private labelHello labelHello;
	private PlayerSkin playerSkin;
	private ManageProfilesButton buttonManageProfiles;

	public Contents() {
		setBounds(0, 0, 854, 480);
		setLayout(null);

		add(buttonClose = new ButtonClose());
		add(buttonOptions = new OptionsButton());
		add(buttonWeb = new WebSiteButton());
		add(buttonLaunch = new LaunchButton(this));
		add(listProfile = new ProfileList(Main.getProfiles()));
		add(textFieldUsername = new LoginTextField(iconLayer));
		add(textfieldPassword = new PasswordTextField(iconLayer));
		add(buttonLogOut = new LogoutButton(this));
		add(playerSkin = new PlayerSkin());
		add(labelHello = new labelHello());
		add(copyrights = new CopyrightLabel());
		add(actionLabel = new ActionLabel());
		add(progressbar = new MyProgressBar());
		add(buttonManageProfiles = new ManageProfilesButton(this));
		setBackground();

	}

	private void setBackground() {
		bg = new JLabel();
		bg.setBackground(Color.BLACK);
		bg.setHorizontalAlignment(SwingConstants.CENTER);
		bg.setIcon(new ImageIcon(BgLayer.class
				.getResource(Variable.resourcePath + "background.png")));
		bg.setBounds(0, 0, 854, 480);
		iconLayer.add(bg);
	}

	public JLabel getBg() {
		return bg;
	}

	public ButtonClose getButtonClose() {
		return buttonClose;
	}

	public OptionsButton getButtonOptions() {
		return buttonOptions;
	}

	public WebSiteButton getButtonWeb() {
		return buttonWeb;
	}

	public LaunchButton getButtonLaunch() {
		return buttonLaunch;
	}

	public ProfileList getListProfile() {
		return listProfile;
	}

	public LoginTextField getTextFieldUsername() {
		return textFieldUsername;
	}

	public PasswordTextField getTextfieldPassword() {
		return textfieldPassword;
	}

	public LogoutButton getButtonLogOut() {
		return buttonLogOut;
	}

	public CopyrightLabel getCopyrights() {
		return copyrights;
	}

	public ActionLabel getActionLabel() {
		return actionLabel;
	}

	public MyProgressBar getProgressbar() {
		return progressbar;
	}

	public labelHello getLabelHello() {
		return labelHello;
	}

	public PlayerSkin getPlayerSkin() {
		return playerSkin;
	}

	public ManageProfilesButton getButtonManageProfiles() {
		return buttonManageProfiles;
	}

	public BgLayer getIconLayer() {
		return iconLayer;
	}

	public void setIconLayer(BgLayer iconLayer) {
		this.iconLayer = iconLayer;
	}

	public void setBg(JLabel bg) {
		this.bg = bg;
	}

	public void setButtonClose(ButtonClose buttonClose) {
		this.buttonClose = buttonClose;
	}

	public void setButtonOptions(OptionsButton buttonOptions) {
		this.buttonOptions = buttonOptions;
	}

	public void setButtonWeb(WebSiteButton buttonWeb) {
		this.buttonWeb = buttonWeb;
	}

	public void setButtonLaunch(LaunchButton buttonLaunch) {
		this.buttonLaunch = buttonLaunch;
	}

	public void setListProfile(ProfileList listProfile) {
		this.listProfile = listProfile;
	}

	public void setTextFieldUsername(LoginTextField textFieldUsername) {
		this.textFieldUsername = textFieldUsername;
	}

	public void setTextfieldPassword(PasswordTextField textfieldPassword) {
		this.textfieldPassword = textfieldPassword;
	}

	public void setButtonLogOut(LogoutButton buttonLogOut) {
		this.buttonLogOut = buttonLogOut;
	}

	public void setCopyrights(CopyrightLabel copyrights) {
		this.copyrights = copyrights;
	}

	public void setActionLabel(ActionLabel actionLabel) {
		this.actionLabel = actionLabel;
	}

	public void setProgressbar(MyProgressBar progressbar) {
		this.progressbar = progressbar;
	}

	public void setLabelHello(labelHello labelHello) {
		this.labelHello = labelHello;
	}

	public void setPlayerSkin(PlayerSkin playerSkin) {
		this.playerSkin = playerSkin;
	}

	public void setButtonManageProfiles(ManageProfilesButton buttonManageProfiles) {
		this.buttonManageProfiles = buttonManageProfiles;
	}

}
