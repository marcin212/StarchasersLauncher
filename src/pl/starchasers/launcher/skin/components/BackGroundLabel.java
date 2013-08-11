package pl.starchasers.launcher.skin.components;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import pl.starchasers.launcher.utils.Variable;

public class BackGroundLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	public static BackGroundLabel instance;
	
	public BackGroundLabel(){
		instance = this;
		setBackground(Color.BLACK);
		setHorizontalAlignment(SwingConstants.CENTER);
		setIcon(new ImageIcon(BackGroundLabel.class.getResource(Variable.resourcePath+"background.png")));
		setBounds(0, 0, 854, 480);
	}
}
