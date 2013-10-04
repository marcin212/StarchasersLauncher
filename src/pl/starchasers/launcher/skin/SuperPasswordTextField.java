package pl.starchasers.launcher.skin;

import java.awt.Color;

import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;


public class SuperPasswordTextField extends JPasswordField{
	private static final long serialVersionUID = 1L;
	private SuperLabelTextField labeltextfield;

	public SuperPasswordTextField(String text, int x, int y, int width, int height, JLayeredPane panel) {
		super(text);
		
		setLabeltextfield(new SuperLabelTextField(x, y, width, height));
		panel.add(getLabeltextfield());
		setBounds(x, y, width, height);
		setForeground(new Color(84, 91, 100));
		setFont(new MyFont().returnFont());
		setOpaque(false);
		setColumns(10);
		setBorder(null);
		
	}

	public SuperLabelTextField getLabeltextfield() {
		return labeltextfield;
	}

	public void setLabeltextfield(SuperLabelTextField labeltextfield) {
		this.labeltextfield = labeltextfield;
	}
}
