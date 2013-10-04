package pl.starchasers.launcher.skin.components;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLayeredPane;

import pl.starchasers.launcher.auth.Login;
import pl.starchasers.launcher.launch.Launch;
import pl.starchasers.launcher.launch.Run;
import pl.starchasers.launcher.skin.SuperPasswordTextField;

public class PasswordTextField extends SuperPasswordTextField{
	private static final long serialVersionUID = 1L;
	public PasswordTextField(JLayeredPane panel){
		super("Password", 510, 319, 152, 27, panel);
		setBounds(510, 319, 152, 27);
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
			
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				if(getText().compareTo("Password")==0)
				setText("");
			}
		});
		addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if(getText().compareTo("Password")==0)
				setText("");
				
			}
		});
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {

				if(e.getKeyCode()==10){
					if(Login.getStatus()){
						Launch.runButton();
					}else if(Login.getCanRun()){
						Run.start();
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
	}
}
