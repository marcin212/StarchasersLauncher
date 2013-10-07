package pl.starchasers.launcher.skin.components;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.UUID;

import javax.swing.JLayeredPane;

import pl.starchasers.launcher.Main;
import pl.starchasers.launcher.launch.Launch;
import pl.starchasers.launcher.launch.Run;
import pl.starchasers.launcher.skin.SuperTextField;

public class LoginTextField extends SuperTextField{
	private static final long serialVersionUID = 1L;
	
	public LoginTextField(JLayeredPane panel) {
		super(Main.getConf().getProperty("nickname").length()!=0 ? Main.getConf().getProperty("nickname"):"Username", 510, 277, 152, 27,panel);
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
					Main.getConf().setProperty("nickname", getText());
					Launch.name = getText();
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
