package pl.starchasers.launcher.skin.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.UUID;

import javax.swing.JTextField;


import pl.starchasers.launcher.launch.Launch;
import pl.starchasers.launcher.launch.Run;
import pl.starchasers.launcher.skin.MyFont;
import pl.starchasers.launcher.utils.Config;

public class LoginTextField extends JTextField {
	private static final long serialVersionUID = 1L;
	public static LoginTextField instance;
	
	public LoginTextField() {
		super(Config.instance.getProperty("nickname").length()!=0 ? Config.instance.getProperty("nickname"):"Username");
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
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {

				if(e.getKeyCode()==112){
					Config.instance.setProperty("nickname", LoginTextField.instance.getText());
					Launch.name = LoginTextField.instance.getText();
					Launch.token = UUID.randomUUID().toString(); 
					Run.start();
					}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
	}

}
