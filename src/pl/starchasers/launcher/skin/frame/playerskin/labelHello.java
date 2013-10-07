package pl.starchasers.launcher.skin.frame.playerskin;

import java.awt.Color;

import javax.swing.JLabel;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.skin.MyFont;

public class labelHello extends JLabel{
	private static final long serialVersionUID = 1L;
	public static labelHello instance;
	public labelHello() {
		instance = this;
		setUserName();
		setOpaque(false);
		setBounds(570, 240, 120, 100);
		setFont(new MyFont().returnFont());
		setForeground(new Color(84, 91, 100));
	}

	public void setUserName() {
		labelHello.instance.setText("Hi, "+ Main.getConf().getProperty("nickname"));
	}
	public void setUserName(String name) {
		labelHello.instance.setText("Hi, "+ name);
	}
}
