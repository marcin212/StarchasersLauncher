package pl.starchasers.launcher.skin.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


import pl.starchasers.launcher.auth.Login;
import pl.starchasers.launcher.utils.Config;
import pl.starchasers.launcher.utils.Variable;

public class LogoutButton extends SuperButton {
	private static final long serialVersionUID = 1L;
	public static LogoutButton instance;

	public LogoutButton(JPanel panel) {
		super(565, 315, 114, 27,"Log Out", Variable.resourcePath + "button_launch",panel);
		instance = this;
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				setIcon(getOverIcon());
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				setIcon(getDownIcon());
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				setIcon(getUpIcon());
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setIcon(getOverIcon());
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				setIcon(getUpIcon());
				Config.instance.setProperty("accessToken", "");
				Login.setCanRun(false);
				Login.setStatus(true);
				LabelLaunch.instance.setText("Log in");
				ActionLabel.instance.setAction("Log in");
				Login.hiddenTextfield(true);
			}
		});
	}
}
