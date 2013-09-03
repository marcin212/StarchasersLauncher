package pl.starchasers.launcher.skin.components;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import pl.starchasers.launcher.skin.MyFont;

public class LabelLaunch extends JLabel {
	private static final long serialVersionUID = 1L;
	private Color upColor = new Color(84, 91, 100);
	private Color downColor = new Color(57, 62, 68);
	private Color overColor = new Color(92, 99, 108);
	public static LabelLaunch instance;

	public LabelLaunch() {
		super("Launch");
		instance = this;
		setForeground(upColor);
		setHorizontalAlignment(SwingConstants.CENTER);
		setOpaque(false);
		setBounds(493, 386, 114, 27);
		setFont(new MyFont().returnFont());
	}
	public LabelLaunch(String name,int x, int y, int width, int height) {
		super(name);
		setForeground(upColor);
		setHorizontalAlignment(SwingConstants.CENTER);
		setOpaque(false);
		setBounds(x, y, width, height);
		setFont(new MyFont().returnFont());
	}
	public void mouseReleased() {
		setForeground(overColor);
	}

	public void mousePressed() {
		setForeground(downColor);
	}

	public void mouseExited() {
		setForeground(upColor);
	}

	public void mouseEntered() {
		setForeground(overColor);
	}

	public void mouseClicked() {
		setForeground(upColor);
	}
}
