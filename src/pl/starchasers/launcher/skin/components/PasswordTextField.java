package pl.starchasers.launcher.skin.components;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPasswordField;

import pl.starchasers.launcher.skin.MyFont;

public class PasswordTextField extends JPasswordField{
	private static final long serialVersionUID = 1L;
	public static PasswordTextField instance;
	public PasswordTextField(){
		super("Password");
		instance = this;
		setBounds(510, 319, 152, 27);
		setOpaque(false);
		setForeground(new Color(84, 91, 100));
		setFont(new MyFont().returnFont());
		setBorder(null);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				setText("");
			}
		});
	}
}
