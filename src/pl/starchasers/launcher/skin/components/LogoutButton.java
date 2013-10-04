package pl.starchasers.launcher.skin.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLayeredPane;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.auth.Login;
import pl.starchasers.launcher.skin.SuperButton;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.Variable;

public class LogoutButton extends SuperButton {
	private static final long serialVersionUID = 1L;

	public LogoutButton(JLayeredPane panel) {
		super(565, 315, 114, 27,"Log Out", Variable.resourcePath + "button_launch",panel);
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Config.instance.setProperty("accessToken", "");
				Login.setCanRun(false);
				Login.setStatus(true);
				((LaunchButton)Main.getFrame().getPanel().getElements().get("LAUNCH")).getButtonLabel().setText("Log in");
				((ActionLabel)Main.getFrame().getPanel().getElements().get("ACTIONLABEL")).setAction("log in");
				Login.hiddenTextfield(true);
			}
		});
	}
}
