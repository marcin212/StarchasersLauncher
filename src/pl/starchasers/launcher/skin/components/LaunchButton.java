package pl.starchasers.launcher.skin.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLayeredPane;

import pl.starchasers.launcher.auth.Login;
import pl.starchasers.launcher.launch.Launch;
import pl.starchasers.launcher.launch.Run;
import pl.starchasers.launcher.skin.SuperButton;
import pl.starchasers.launcher.utils.Variable;

public class LaunchButton extends SuperButton{
	private static final long serialVersionUID = 1L;
	public LaunchButton(JLayeredPane panel) {
		super(493, 386, 114, 27,"Launch", Variable.resourcePath+"button_launch",panel);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {

			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {

			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {

			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {

			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(Login.getStatus()){
					Launch.runButton();
				}else if(Login.getCanRun()){
					Run.start();
				}

			}
		});
	}

	

}
