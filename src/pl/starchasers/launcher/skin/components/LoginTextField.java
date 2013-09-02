package pl.starchasers.launcher.skin.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import pl.starchasers.launcher.auth.Login;
import pl.starchasers.launcher.skin.MyFont;

public class LoginTextField extends JTextField {
	private static final long serialVersionUID = 1L;
	public static LoginTextField instance;
	
	public LoginTextField() {
		super("Username");
		instance = this;
		setBounds(510, 277, 152, 27);
		setForeground(new Color(84, 91, 100));
		setFont(new MyFont().returnFont());
		setOpaque(false);
		setColumns(10);
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
				if(getText().compareTo("Username")==0)
				setText("");
				
			}
		});
		addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(getText().compareTo("Username")==0)
				setText("");
				
			}
		});
	}

}
