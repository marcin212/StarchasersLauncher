package pl.starchasers.launcher.skin.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import pl.starchasers.launcher.utils.Variable;

public class BgUserNameTextField extends JLabel{
	private static final long serialVersionUID = 1L;
	public static BgUserNameTextField instance;
	
	public BgUserNameTextField(){
		instance = this;
		setIcon(new ImageIcon(BgUserNameTextField.class.getResource(Variable.resourcePath+"textfield.png")));
		setBounds(492, 277, 176, 27);
	}
}
