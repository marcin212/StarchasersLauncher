package pl.starchasers.launcher.skin.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import pl.starchasers.launcher.utils.Variable;

public class BgPasswordTextField extends JLabel{
	private static final long serialVersionUID = 1L;
	
	public BgPasswordTextField(){
		setIcon(new ImageIcon(BgPasswordTextField.class.getResource(Variable.resourcePath+"textfield.png")));
		setBounds(492, 319, 176, 27);
	}
}
