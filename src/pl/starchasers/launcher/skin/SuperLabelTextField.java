package pl.starchasers.launcher.skin;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import pl.starchasers.launcher.utils.Variable;

public class SuperLabelTextField extends JLabel{
	private static final long serialVersionUID = 1L;

	public SuperLabelTextField(int x, int y, int width, int height){
		setIcon(new ImageIcon(SuperLabelTextField.class.getResource(Variable.resourcePath+"textfield.png")));
		setBounds(x-18, y, width+24, height);
	}
}
